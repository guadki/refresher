package types

object StrTypes {

  private val a: Char = 'a'
  private val str: String = "abc"

  private val strInterpolation = s"$a b c $str ${1 + 10}"

  private val multiline: String =
    """Gęby ponure, spojrzenia tępe...
      |- Z czym to walczycie, bracia?
      |- Z postępem...""".stripMargin
}
