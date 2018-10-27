package services

import models.DataStatistics
import play.api.libs.json.{JsValue, _}

import scala.util.{Failure, Success, Try}

  object JsonExtractor {

    /** this function will validate the json and then extract the event_type and data columns
      *
      * @param json
      * @return
      */
    def extract(json: String) : Option[DataStatistics] = {
      val result = validateJson(json)
      if (result.isDefined) {
        extractData(result.get)
      } else  {
        None
      }
    }


  /** this function trites to parse the json if it is valid it returns JsValue otherwise it returns None
    *
    * @param json
    * @return
    */
  def validateJson(json: String) : Option[JsValue] = {
    // convert string to json value
    val tryJsonObject: Try[JsValue] = Try(Json.parse(json))

    tryJsonObject match {
      case Success(value) => Some(value)
      case Failure(exception) => {
        println(exception)
        None
      }
    }
  }


  /** this function extracts the event_type and data from the given json object in case it exists
    *
    * @param jsonObject
    * @return
    */
  def extractData(jsonObject: JsValue) : Option[DataStatistics] = {

    // get the event_type from the json
    val eventType = (jsonObject \ "event_type") match {
      case success: JsDefined =>
        Some(success.as[String])
      case failure: JsUndefined =>
        None
    }

    // get the data from the json
    val wordsCount = (jsonObject \ "data") match {
      case success: JsDefined =>
        val data = success.as[String]
        val wordsMap = data.split(" ").groupBy((word: String) => word).mapValues(_.length)
        Some(wordsMap)
      case failure: JsUndefined =>
        None
    }

    // in case both are valid we return a DataStatistics object
    if (eventType.isDefined && wordsCount.isDefined) {
      Some(DataStatistics(eventType.get, wordsCount.get))
    }
    else {
      None
    }
  }
}
