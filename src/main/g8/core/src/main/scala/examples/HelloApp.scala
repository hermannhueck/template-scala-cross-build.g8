package examples

import scala.util.chaining._

object HelloApp extends Greeting with hutil.App {

  greeting pipe println
}

trait Greeting {
  lazy val greeting: String = "hello"
}
