package hutilexamples

import java.io.{BufferedReader, FileReader}

import scala.util.chaining._
import scala.util.Using
import hutil.stringformat._
import hutil.syntax.pipe._
import compat213.collections.unfold._

object UnfoldUsingApp extends hutil.App {

  val unfoldFunction: Int => Option[(Int, Int)] = {
    case 0 => None
    case s => Some(((s * s), (s - 1)))
  }

  textInLine("List.unfold") | println

  List.unfold(10)(unfoldFunction) pipe println

  textInLine("Iterator.unfold + Using") | println

  def bufferedReader(fileName: String): BufferedReader =
    new BufferedReader(new FileReader(fileName))

  def readLines(reader: BufferedReader): Seq[String] =
    Iterator
      .unfold(()) { _ => Option { reader.readLine() }.map(x => (x, ())) }
      .toList

  val lines: Seq[String] =
    Using.resource(bufferedReader("README.md"))(readLines)

  lines foreach println
}
