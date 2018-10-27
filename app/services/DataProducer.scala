package services

import akka.actor.ActorSystem
import javax.inject.{Inject, Singleton}
import models.DataStatistics
import rx.lang.scala._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.sys.process._
import utils.Globals._


@Singleton
class DataProducer @Inject()(actorSystem: ActorSystem)(implicit executionContext: ExecutionContext)  {

  actorSystem.scheduler.scheduleOnce(delay = 0.seconds) {
    // access the exe stream of data
    val contentsStream: Stream[String] = Process(exePath).lineStream


    // create observable from the exe output stream
    val contentsObservable: Observable[String] =  Observable.from(contentsStream)

    // validate the json and then extract the wanted data
    val dataStatsObservable: Observable[DataStatistics] = contentsObservable.map(JsonExtractor.extract)
                                                .collect{ case stats: Some[DataStatistics] => stats.get}


    // create a hander that will deal with saving the new data
    val localHandlerObserver: LocalHandlerObserver = new LocalHandlerObserver

    // subscrible the handler to the data stats observable
    dataStatsObservable.subscribe(localHandlerObserver.instance)
  }

}
