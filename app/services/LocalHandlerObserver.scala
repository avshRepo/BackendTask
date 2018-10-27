package services

import models.DataStatistics
import rx.lang.scala.Observer
import utils.Globals._

class LocalHandlerObserver {
  // this observer updates the events and words variables
  val instance = Observer[DataStatistics] (
    onNext = (stats: DataStatistics) => {
      eventsCountByType.update(stats.eventType, eventsCountByType.get(stats.eventType).getOrElse(0) + 1)
      wordsCountByApperance = wordsCountByApperance ++
                              stats.wordsCount.map{case (k,v) => k -> (v + wordsCountByApperance.getOrElse(k, 0))}
      },
    onError = (e: Throwable) => println(e)
  )
}
