package other

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

object ByNameParams {

  /**
   * Parameters are normally evaluated when the method is called. By-name parameters allow as to defer evaluating
   * until parameter is called in method body (if at all)
   *
   * `heavyComputation` will be evaluated after reaching its call in method body:
   * {{{  computeSomethingHeavy(isNecessary = true, heavyComputation = 1 + 2 + 3)}}}
   *
   * `heavyComputation` will stay as `10 + 20 + 30` because it was never called:
   * {{{  computeSomethingHeavy(isNecessary = false, heavyComputation = 10 + 20 + 30)}}}
   */
  def computeSomethingHeavy(isNecessary: Boolean, heavyComputation: => Int): Int = {
    if (isNecessary) heavyComputation else 44
  }

  /**
   * By-name parameters allow wrapping method with other code that needs to defer evaluation to work properly.
   * In this form method can be easily substituted in algorithm to check if something unintended is happening with
   * method running time.
   */
  def measureRunningTime[T](name: String)(f: => T): T = {
    val t1 = System.currentTimeMillis()
    val result: T = f
    val t2 = System.currentTimeMillis()
    println(s"[$name] ${t2 - t1}ms")
    result
  }

  /**
   * By-name parameters can be used to repeatedly evaluate method or block of code
   *
   * Example usage:
   * {{{
   *    other.ByNameParams.retry(10) {
   *      if (Random.nextInt(3) == 2) "Hurray!" else throw new FileNotFoundException()
   *    }.map(println)
   * }}}
   *
   */
  def retry[T](max: Int)(f: => T): Try[T] = {
    retryRec(max, f)
  }

  @tailrec
  private def retryRec[T](max: Int, f: => T, curr: Int = 0): Try[T] = {
    curr match {
      case c if c >= max => Failure(MaxRetriesReachedException())
      case c if c < max =>
        Try(f) match {
          case x: Success[T] => x
          case _: Failure[T] => retryRec(max, f, curr + 1)
        }
    }
  }

}
