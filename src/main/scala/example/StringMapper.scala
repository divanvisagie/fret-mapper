package example

class StringMapper {
  val noteMapper = NoteMapper()

  def getStringStartingWith(note: String): Array[String] = {
    val numbers = 0 until 12

    numbers.foldLeft(Array[String]()) { (acc, i) =>
      if (i == 0) {
         Array(noteMapper.next(note))
      } else {
        acc ++ Array(noteMapper.next(acc.last))
      }
    }
  }
}

object StringMapper {
  def apply(): StringMapper = new StringMapper()
}
