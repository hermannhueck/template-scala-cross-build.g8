package hutil

import java.lang.System.{currentTimeMillis => currentTime}
import scala.collection.mutable.ListBuffer
// import scala.util.Using
import hutil.stringformat._

@com.github.ghik.silencer.silent("deprecated")
trait App extends DelayedInit {

  final val executionStart: Long = currentTime

  final protected def args: Array[String] = _args

  private[this] var _args: Array[String] = _

  private[this] val initCode = new ListBuffer[() => Unit]

  def execBody(body: => Unit): Unit =
    try {
      // printHeaderWithProgramName(this)
      printHeaderWithProgramName(this)
      body
    } finally {
      val total = currentTime - executionStart
      printFooter(s"[total: \$total ms]")
    }

  override def delayedInit(body: => Unit): Unit = {
    initCode += (() => execBody(body))
  }

  final def main(args: Array[String]) = {
    this._args = args
    for (proc <- initCode) proc()
  }
}
