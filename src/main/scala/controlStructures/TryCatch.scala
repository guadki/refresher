package controlStructures

import java.io.{FileNotFoundException, IOException}

object TryCatch {

  try {
    println("123")
  } catch {
    case fileNotFoundException: FileNotFoundException => println("file not found")
    case ioException: IOException => println("io exception")
  }

}
