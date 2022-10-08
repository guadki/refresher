package collections

object CustomOrdering {

  /**
   * [[Ordered]] allows sorting of a class with [[scala.util.Sorting]] and can be used with comparison operators
   * {{{
   *    val b1 = Book("afs234hbb1", "Brave New World", "12345678")
   *    val b2 = Book("as80fhj227", "1984", "87654321")
   *
   *    val resB = b1 > b2
   *    val listB = List(b1, b2).sorted
   * }}}
   */
  case class Book(id: String, title: String, isbn: String) extends Ordered[Book] {
    override def compare(that: Book): Int = this.id.compare(that.id)
  }

  case class Person(name: String, surname: String, personalId: String)

  /**
   * [[Ordering]] is used when there is a need for multiple different orderings implementations
   * {{{
   *    private val p1 = Person("Alan", "Nala", "afs234hbb1")
   *    private val p2 = Person("Kuba", "Abuk", "as80fhj227")
   *
   *    val list = List(p1, p2).sorted(SurnameOrdering)
   * }}}
   */
  object PersonalIdOrdering extends Ordering[Person] {
    override def compare(x: Person, y: Person): Int = x.personalId.compare(y.personalId)
  }

  object SurnameOrdering extends Ordering[Person] {
    override def compare(x: Person, y: Person): Int = x.surname.compare(y.surname)
  }

}
