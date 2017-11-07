package fretmapper.core


object StringMapper {

  def getStringStartingWith(note: String): Array[String] = {

    //TODO: remove magic numbers
    val numbers = 0 until 12

    numbers.foldLeft(Array[String]()) { (acc, i) =>
      if (i == 0) {
        Array(Note.next(note))
      } else {
        acc ++ Array(Note.next(acc.last))
      }
    }
  }
}
