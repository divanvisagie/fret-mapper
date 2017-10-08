package fretmapper.core


object StringMapper {

  def getStringStartingWith(note: String): Array[String] = {
    val numbers = 0 until 12

    numbers.foldLeft(Array[String]()) { (acc, i) =>
      if (i == 0) {
        Array(NoteMapper.next(note))
      } else {
        acc ++ Array(NoteMapper.next(acc.last))
      }
    }
  }
}
