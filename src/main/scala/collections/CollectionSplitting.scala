package collections

object CollectionSplitting {

  private val col = Vector(15, 5, 20, 12, 8, 13, 3)

  sealed trait CheckRange

  case object LessThan10 extends CheckRange

  case object MoreOrEq10LessThan15 extends CheckRange

  case object MoreOrEq15 extends CheckRange

  val groups: Map[CheckRange, Vector[Int]] = col.groupBy {
    case x if x < 10 => LessThan10
    case x if x < 15 => MoreOrEq10LessThan15
    case x if x >= 15 => MoreOrEq15
  }

  /**
   * `part1` is a collection of elements that meet the condition `_ < 12`. `part2` is the rest
   */
  val (part1, part2) = col.partition(_ < 12)

  /**
   * [[range]] is first subset of elements from [[col]], starting at `col(0)` for which `_ < 12` is true
   */
  val (range, rest) = col.span(_ < 12)

  val (x, y) = col.splitAt(4)

}
