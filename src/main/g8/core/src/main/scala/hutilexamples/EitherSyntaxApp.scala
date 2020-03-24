package hutilexamples

import compat213.either._
import hutil.syntax.either._
import hutil.syntax.pipe._

object EitherSyntaxApp extends hutil.App {

  Right(42)
    .withLeft[String]
    .leftMap(_.toLowerCase)
    .ensuring(_ == Right(42)) | println

  Left("Error")
    .withRight[Int]
    .leftMap(_.toLowerCase)
    .ensuring(_ == Left("error")) | println

  println

  Right(Right(42)).flatten | println
  Right(Left("error")).flatten | println
  Left(Right(42)).flatten | println
  Left(Left("error")).flatten | println
}
