package controllers

import javax.inject._

import play.api._
import play.api.cache.redis.CacheApi
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation._
import play.api.data.validation.Constraints._
import play.api.mvc._
import play.api.libs.json._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext

@Singleton
class GroupController @Inject()(cache: CacheApi, cc: ControllerComponents)
                               (implicit executionContext: ExecutionContext)
                               extends AbstractController(cc)
                               with play.api.i18n.I18nSupport {

  // TODO define more parameters for this:
  /* IDEA
   * 1. List of locations for incoming users to track
   * 2. Duration active/event dates/times
   */
  case class GroupData(name: String, members: List[String])

  // Confirm that there is at least one element in our list
  // TODO escape characters
  def nonEmptyList[T]: Constraint[List[T]] = Constraint[List[T]]("constraint.required") { o =>
    if (o.nonEmpty) Valid else Invalid(ValidationError("error.required"))
  }

  // Confirm that our name value is in the Redis cache
  // XXX Do we want to do this here? It might make testing hard
  def notInCache[String]: Constraint[String] = Constraint[String]("cache.exists") {name =>
    if (!cache.exists(s"${name.toString.toLowerCase}.members")) Valid
    else Invalid(ValidationError("Group name taken"))
  }

  val groupForm = Form(
    mapping(
      "name" -> nonEmptyText.verifying(notInCache),
      "members" -> list(text.verifying(nonEmpty)).verifying(nonEmptyList)
    )(GroupData.apply)(GroupData.unapply)
  )

  /** createGroup
   *  Endpoint to create a new group to play taco golf
   *  @param request a json request formatted with:
   *                 name -> A unique name identifier for the group
   *                 members -> A list of unique, non-empty group members
   *  @return The name of the created group in JSON
   */
  def createGroup = Action(parse.json) { implicit request =>
    groupForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(formWithErrors.errorsAsJson.toString)
      },
      groupData => {
        val keyPrefix = groupData.name.toLowerCase
        cache.set[String](s"${keyPrefix}.members").add(groupData.members:_*)
        cache.expire(s"${keyPrefix}.members", 7 days)
        Created(Json.obj("name" -> keyPrefix))
      }
    )
  }

  /** getGroup
   *  Endpoint to get an existing group from Redis
   *  @param name the unique string identifier of the group
   *  @return On Success => a JSON object formatted the same as the params for
   *                        createGroup
   *          On Failure => 404 for an unidentified group name
   */
  def getGroup(name: String) = Action { implicit request =>
    cache.set[String](s"${name.toLowerCase}.members").toSet match {
      case members if members.nonEmpty => Ok(Json.obj(
        "name" -> name.toLowerCase,
        "members" -> Json.toJson(members)
      ))
      case _ => NotFound(Json.obj(
        "Not Found" -> s"${name.toLowerCase}"
      ))
    }
  }

  /** deleteGroup
   *  Endpoint to remove an existing group from Redis
   *  @param name the unique string identifier of the group
   *  @return On success => an empty response confirming the group was deleted
   *          On failure => 404 for an unidentified group name
   */
  def deleteGroup(name: String) = Action { implicit request =>
    if (cache.exists(s"${name.toLowerCase}.members")) {
      cache.remove(s"${name.toLowerCase}.members")
      NoContent
    }
    else NotFound(Json.obj("Not Found" -> s"${name.toLowerCase}"))
  }
}
