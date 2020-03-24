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
      munit,
      collectionCompat,
      silencerLib,
      silencerPlugin,
      kindProjectorPlugin,
      betterMonadicForPlugin
    ) ++ Seq(
      scalaTest,
      scalaCheck,
      scalaTestPlusCheck,
      scalaCheckShapeless
    ).map(_ % Test),
    Test / parallelExecution := false,
    // S = Small Stack Traces, D = print Duration
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oSD"),
    // run 100 tests for each property // -s = -minSuccessfulTests
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaCheck, "-s", "100"),
    testFrameworks += new TestFramework("munit.Framework"),
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
  .dependsOn(hutil)
  .settings(
    name := "core",
    description := "My gorgeous core App",
    scalacOptions ++= scalacOptionsFor(scalaVersion.value),
    console / scalacOptions := removeScalacOptionXlintUnusedForConsoleFrom(scalacOptions.value),
    libraryDependencies ++= Seq(
      shapeless,
      fs2Io
    ),
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-compiler" % scalaVersion.value,
      "org.scala-lang" % "scala-reflect"  % scalaVersion.value
    )
  )

lazy val hutil = (project in file("hutil"))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "hutil",
    description := "Hermann's Utilities",
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "build",
    scalacOptions ++= scalacOptionsFor(scalaVersion.value)
  )
