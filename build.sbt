name := "neko"

version := "0.1"

scalaVersion := "2.12.8"

scalacOptions += "-Ypartial-unification"

val circeVersion = "0.10.0"

libraryDependencies += "org.typelevel" %% "cats-core" % "1.5.0"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)


