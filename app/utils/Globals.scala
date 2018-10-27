package utils


import scala.collection.mutable.{Map => mutableMap}
object Globals {
  var eventsCountByType: mutableMap[String, Int] = mutableMap[String, Int]().withDefaultValue(0)
  var wordsCountByApperance: mutableMap[String, Int] = mutableMap[String, Int]().withDefaultValue(0)
  val exePath = getClass.getClassLoader.getResource("resources/generator-windows-amd64.exe").getPath
  }
