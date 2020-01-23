import Dependencies._
import ScalacOptions._

val projectName        = "$project_name$"
val projectDescription = "$project_description$"
val projectVersion     = "$project_version$"

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
      scalactic,
      scalaTest,
      scalaMock,
      shapeless,
      catsEffect
    ) ++ Seq(
      scalaCheck,
      scalaTestPlusCheck,
      scalaCheckShapeless
    ).map(_ % Test),
    Test / parallelExecution := false,
    // S = Small Stack Traces, D = print Duration
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oSD"),
    initialCommands :=
      s"""|
          |import scala.util.chaining._
          |println
          |""".stripMargin // initialize REPL
  )
)

lazy val root = (project in file("."))
  .aggregate(core)
  .settings(
    name := projectName,
    description := projectDescription,
    crossScalaVersions := Seq.empty
  )

lazy val core = (project in file("core"))
  .dependsOn(compat213, util)
  .settings(
    name := "core",
    description := "My gorgeous core App",
    libraryDependencies ++= Seq(
      shapeless,
      fs2Core,
      fs2Io
    ) ++ {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, minor)) if minor >= 13 => Seq.empty
        // Macro paradise not needed in 2.13. Just use scalacOption -Ymacro-annotations. See project/ScalacOptions.scala
        case _ =>
          Seq(compilerPlugin("org.scalamacros" %% "paradise" % "2.1.1" cross CrossVersion.full))
      }
    },
    scalacOptions ++= scalacOptionsFor(scalaVersion.value),
    // suppress unused import warnings in the scala repl
    console / scalacOptions := removeScalacOptionXlintUnusedForConsoleFrom(scalacOptions.value)
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
