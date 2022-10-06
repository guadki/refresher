package other

import scala.reflect.ClassTag

object Implicits {

  trait StringParser[T] {
    def parse(s: String): T
  }

  object StringParser {
    implicit object ParseString extends StringParser[String] {
      def parse(s: String): String = s
    }

    implicit object ParseInt extends StringParser[Int] {
      def parse(s: String): Int = s.toInt
    }

    implicit object ParseBoolean extends StringParser[Boolean] {
      def parse(s: String): Boolean = s.toBoolean
    }

    implicit object ParseDouble extends StringParser[Double] {
      def parse(s: String): Double = s.toDouble
    }

    implicit def ParseSeq[T](implicit p: StringParser[T], c: ClassTag[T]): StringParser[Seq[T]] = {
      (s: String) => s.drop(1).dropRight(1).split(',').map(_.trim).map(p.parse)
    }

    implicit def ParseTuple[T, V](implicit p1: StringParser[T], p2: StringParser[V]): StringParser[(T, V)] = {
      (s: String) => {
        val Array(left, right) = s.drop(1).dropRight(1).split(',').map(_.trim)
        (p1.parse(left), p2.parse(right))
      }
    }
  }

  /**
   * As long as there is implicit parser for used type in the scope this method will convert supplied `String` to
   * that type
   */
  def parseFromString[T](s: String)(implicit parser: StringParser[T]): T = {
    parser.parse(s)
  }

}
