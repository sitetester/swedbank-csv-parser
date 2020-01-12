name := "swedbank-csv-parser"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.xerial" % "sqlite-jdbc" % "3.7.2"
)

libraryDependencies += "org.jsoup" % "jsoup" % "1.8.3"

