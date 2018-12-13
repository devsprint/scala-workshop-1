name := "workshop"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.17",
  "org.typelevel" %% "cats-core" % "1.4.0",
  "org.typelevel" %% "cats-effect" % "1.0.0",
  "org.scalactic" %% "scalactic" % "3.0.5" % Test,
   "org.scalatest" %% "scalatest" % "3.0.5" % Test
)
  