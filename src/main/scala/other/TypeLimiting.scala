package other

import scala.language.reflectiveCalls

object TypeLimiting {
  /**
   * `Type A` must have method `ready(): B` and field `input` defined.
   */
  def init[A <: {def ready(input: String): B; val input: String}, B](status: A): B = {
    status.ready(status.input)
  }
}
