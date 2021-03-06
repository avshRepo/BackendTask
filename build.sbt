name := """play-scala-starter-example"""

version := "1.0-SNAPSHOT"
javaHome := sys.env.get("JAVA_HOME") map file
lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.6")

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.h2database" % "h2" % "1.4.197"
libraryDependencies += "io.reactivex" % "rxscala_2.12" % "0.26.5"