package example

import util.formatting._

object Hello extends Greeting with util.App {

  println(greeting)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
