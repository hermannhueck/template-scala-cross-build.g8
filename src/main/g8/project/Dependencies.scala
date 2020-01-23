import sbt._

object Dependencies {

  val collectionCompatVersion    = "2.1.3"
  val shapelessVersion           = "2.3.3"
  val catsVersion                = "2.0.0"
  val fs2Version                 = "2.2.1"
  val zioVersion                 = "1.0.0-RC17"
  val scalaTestVersion           = "3.1.0"
  val scalaMockVersion           = "4.4.0"
  val scalaCheckVersion          = "1.14.3"
  val scalaCheckShapelessVersion = "1.2.3"

  val collectionCompat    = "org.scala-lang.modules"     %% "scala-collection-compat"   % collectionCompatVersion
  val shapeless           = "com.chuusai"                %% "shapeless"                 % shapelessVersion
  val catsEffect          = "org.typelevel"              %% "cats-effect"               % catsVersion
  val fs2Core             = "co.fs2"                     %% "fs2-core"                  % fs2Version
  val fs2Io               = "co.fs2"                     %% "fs2-io"                    % fs2Version
  val zio                 = "dev.zio"                    %% "zio"                       % zioVersion
  val zioStreams          = "dev.zio"                    %% "zio-streams"               % zioVersion
  val zioTest             = "dev.zio"                    %% "zio-test"                  % zioVersion
  val zioTestSbt          = "dev.zio"                    %% "zio-test-sbt"              % zioVersion
  val scalactic           = "org.scalactic"              %% "scalactic"                 % scalaTestVersion
  val scalaTest           = "org.scalatest"              %% "scalatest"                 % scalaTestVersion
  val scalaTestPlusCheck  = "org.scalatestplus"          %% "scalatestplus-scalacheck"  % "3.1.0.0-RC2"
  val scalaMock           = "org.scalamock"              %% "scalamock"                 % scalaMockVersion
  val scalaCheck          = "org.scalacheck"             %% "scalacheck"                % scalaCheckVersion
  val scalaCheckShapeless = "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % scalaCheckShapelessVersion
}
