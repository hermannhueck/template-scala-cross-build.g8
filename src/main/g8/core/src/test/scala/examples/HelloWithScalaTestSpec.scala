package examples

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class HelloWithScalaTestSpec extends AnyFlatSpec with Matchers {
  "The Hello object" should "say hello" in {
    HelloApp.greeting shouldEqual "hello"
  }
}