package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import play.api.cache.redis.CacheApi

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

@Singleton
class HomeController @Inject()(cache: CacheApi, cc: ControllerComponents)
                              (implicit executionContext: ExecutionContext)
                              extends AbstractController( cc ) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(cache.getOrElse("test", expiration = 10.seconds)("Not Found").toString)
  }

}
