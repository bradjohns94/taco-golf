package controllers

import javax.inject._

import play.api._
import play.api.cache.redis.CacheApi
import play.api.mvc._
import play.api.libs.json._

import scala.concurrent.ExecutionContext

@Singleton
class GroupController @Inject()(cache: CacheApi, cc: ControllerComponents)
                               (implicit executionContext: ExecutionContext)
                               extends AbstractController(cc) {

  def createGroup = Action { request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson

    jsonBody.map {json =>
      Ok("Got: " + json.toString)
    }.getOrElse {
      BadRequest("Fuck")
    }
  }
}
