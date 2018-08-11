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
  def nonEmptyList[T]: Constraint[List[T]] = Constraint[List[T]]("constraint.required") { o =>
    if (o.nonEmpty) Valid else Invalid(ValidationError("error.required"))
  }

  def notInCache[String]: Constraint[String] = Constraint[String]("cache.exists") {name =>
    if (cache.exists(name.toString)) Valid else Invalid(ValidationError("Group name taken"))
  }

  val groupForm = Form(
    mapping(
      "name" -> text.verifying(nonEmpty),
      "members" -> list(text.verifying(nonEmpty)).verifying(nonEmptyList)
    )(GroupData.apply)(GroupData.unapply)
  )

  def createGroup = Action(parse.json) { implicit request =>
    groupForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(formWithErrors.errorsAsJson.toString)
      },
      groupData => {
        cache.list[String](s"${groupData.name}.members") :++ groupData.members
        cache.expire(s"${groupData.name}.members", 7 days)
        Created(Json.obj("name" -> groupData.name))
      }
    )
  }
}
