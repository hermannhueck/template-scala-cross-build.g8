package hutil
package syntax

package object pipe {

  implicit class FSharpPipeOperator[A](private val self: A) extends AnyVal {
    @inline def |>[B](f: A => B): B = f(self)
  }
}
