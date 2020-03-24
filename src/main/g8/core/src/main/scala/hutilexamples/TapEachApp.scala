package hutilexamples

import scala.util.chaining._
import hutil.stringformat._
import hutil.syntax.pipe._
import compat213.collections.tapeach._

object TapEachApp extends hutil.App {

  "Coll#tapEach".magenta | (s => println(s"\$s\n"))

  val doubledAndSquared: List[Int] =
    List(1, 2, 3)
      .tapEach(x => println(s"value: \$x"))
      .tap(_ => println)
      .map(x => x * 2)
      .tapEach(x => println(s"doubled: \$x"))
      .tap(_ => println)
      .map(x => x * x)
      .tapEach(x => println(s"squared: \$x"))
}
