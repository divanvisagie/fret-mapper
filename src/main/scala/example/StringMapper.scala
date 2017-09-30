package example

class StringMapper {
  val noteMapper = NoteMapper()

  def getStringStartingWith(note: String): Array[String] = {
    val numbers = (0 until 12)

    numbers.foldLeft(Array[String]()) { (acc, i) =>
      if (i == 0) {
         Array(noteMapper.getNextNote(note))
      } else {
        acc ++ Array(noteMapper.getNextNote(acc.last))
      }
    }
  }
}

object StringMapper {
  def apply(): StringMapper = new StringMapper()
}
