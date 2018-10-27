package controllers

import akka.actor.ActorSystem
import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import utils.Globals._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class StatsController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc) {

  /**
    * returns hello when entering the page
    * @return
    */
  def index = Action {
     Ok("hello")
  }
  /**
    * returns a dictionary where each line contains the event and the number of times it appeared so far
    * @return
    */
  def getEventsCountByType= Action.async {
    val eventsCountByTypeFuture = Future {
      Json.stringify(Json.toJson(eventsCountByType))
    }
    eventsCountByTypeFuture.map { msg => Ok(msg) }
  }

  /**
    * returns a dictionary where each line contains the word and the number of times it appeared so far
    * @return
    */
  def getWordsCount = Action.async {
      val wordCountFuture = Future {
        Json.stringify(Json.toJson(wordsCountByApperance))
      }
      wordCountFuture.map { msg => Ok(msg) }
    }
}
