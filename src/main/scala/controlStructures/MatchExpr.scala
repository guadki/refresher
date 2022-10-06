package controlStructures

object MatchExpr {

  private val num = 2

  private val result: String = num match {
    case 1 => "one"
    case 2 => "two"
    case _ => "other"
  }

  def classAsStr(x: Any): String = {
    x match {
      case s: String => s + " is a String"
      case _: Int => "Int"
      case _: Float => "Float"
      case _ => "Unknown"
    }
  }

}
