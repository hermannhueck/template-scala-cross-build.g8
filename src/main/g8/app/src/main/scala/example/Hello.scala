package example

import util.formatting._

object Hello extends Greeting with App {

  prtTitleObjectName(this)

  println(greeting)

  prtLine()
}

trait Greeting {
  lazy val greeting: String = "hello"
}
