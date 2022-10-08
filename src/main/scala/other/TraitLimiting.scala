package other

object TraitLimiting {

  class Fruit(name: String)

  /**
   * Only subclass of [[Fruit]] can have `trait` [[Juicy]] mixed into it
   */
  trait Juicy {
    this: Fruit =>
    def taste(): Unit
  }

  /**
   * `Class` that wants to have `trait` [[Sour]] mixed into it must have `method` `taste()` defined
   */
  trait Sour {
    this: {def taste(bit: String): String} =>
  }

}
