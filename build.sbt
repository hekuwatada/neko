name := "neko"

version := "0.1"

scalaVersion := "2.12.8"

scalacOptions += "-Ypartial-unification"

val catsCoreVersion = "1.5.0"
val catsEffectVersion = "1.2.0"
val circeVersion = "0.10.0"

val circeLibs = Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

val prodLibs = Seq(
  "org.typelevel" %% "cats-core" % catsCoreVersion,
  "org.typelevel" %% "cats-effect" % catsEffectVersion
) ++ circeLibs

val testLibs = Seq(
  "org.typelevel" %% "cats-effect-laws" % catsEffectVersion % Test
)

libraryDependencies ++= prodLibs ++ testLibs

