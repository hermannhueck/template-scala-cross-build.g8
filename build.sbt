// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.

val projectName = "template-scala-cross-build"

val projectDescription =
  "Giter8 template for sbt project cross building to scala versions 2.13 and 2.12"

lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    name := projectName,
    description := projectDescription,
    scriptedLaunchOpts ++= List(
      "-Xms1g",
      "-Xmx1g",
      "-XX:ReservedCodeCacheSize=128m",
      "-Xss2m",
      "-Dfile.encoding=UTF-8"
    )
  )

addCommandAlias("test", "g8Test")
