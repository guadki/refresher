package controlStructures

object ForLoop {

  for (i <- 1 to 5) yield i * 2

  private val fruits = List("apple", "banana", "lime", "orange")

  for {f <- fruits if f.length > 4} yield f.charAt(0)

}
