package other

object ImplicitClass {

  /**
   * Allows to define new methods to existing classes.
   *
   * If `implicit class` is in the scope, [[increment]] method can be called directly on [[String]]:
   * {{{    "ABC".increment}}}
   */
  implicit class StringExtension(val s: String) {
    def increment: String = s.map(c => (c + 1).toChar)
  }

}
