package hutil
package syntax

package object pipe {

  implicit final class PipeOperator[A](private val self: A) extends AnyVal {
    @inline def |[B](f: A => B): B = f(self)
  }
}
