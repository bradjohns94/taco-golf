name := """taco-golf-api"""
organization := "taco.golf"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.github.karelcemus" %% "play-redis" % "2.2.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  play.sbt.PlayImport.cacheApi,
  guice
)
