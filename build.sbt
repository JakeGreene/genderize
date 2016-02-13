name := """genderize"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-experimental" % "2.0.3"
  ,"org.scalatest"     %% "scalatest"             % "2.2.4" % "test"
)
