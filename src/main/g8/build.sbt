import Dependencies._
import ScalacOptions._

val projectName        = "$name$"
val projectDescription = "Project Description (CHANGE IT!!!)"
val projectVersion     = "$version$"

val scala212               = "$scala212_latest_version$"
val scala213               = "$scala_latest_version$"
val supportedScalaVersions = List(scala212, scala213)

inThisBuild(
  Seq(
    version := projectVersion,
    scalaVersion := scala213,
    crossScalaVersions := supportedScalaVersions,
    publish / skip := true,
    libraryDependencies ++= Seq(
      collectionCompat,
      shapeless,
      scalaTest  % Test,
      scalaCheck % Test
    ),
    initialCommands :=
      s"""|
          |import scala.util.chaining._
          |import util.syntax.pipe._
          |println
          |""".stripMargin // initialize REPL
  )
)

lazy val root = (project in file("."))
  .aggregate(app)
  .settings(
    name := projectName,
    description := projectDescription,
    crossScalaVersions := Seq.empty
  )

lazy val app = (project in file("app"))
  .dependsOn(compat213, util)
  .settings(
    name := "app",
    description := "My gorgeous App",
    scalacOptions ++= scalacOptionsFor(scalaVersion.value),
    // suppress unused import warnings in the scala repl
    console / scalacOptions := scalacOptions.value :+ "-Xlint:-unused,_",
    libraryDependencies ++= Seq(
      shapeless,
      catsCore
    )
  )

lazy val compat213 = (project in file("compat213"))
  .settings(
    name := "compat213",
    description := "compat library providing features of Scala 2.13 backported to 2.12",
    scalacOptions ++= scalacOptionsFor(scalaVersion.value)
  )

lazy val util = (project in file("util"))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "util",
    description := "Utilities",
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "build",
    scalacOptions ++= scalacOptionsFor(scalaVersion.value)
  )

// https://github.com/typelevel/kind-projector
addCompilerPlugin("org.typelevel" % "kind-projector" % "0.11.0" cross CrossVersion.full)
// https://github.com/oleg-py/better-monadic-for
addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
