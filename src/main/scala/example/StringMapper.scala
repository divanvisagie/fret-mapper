package example

class StringMapper {
  def getStringStartingWith(note: String): Array[String] = {
    Array(
      "C#", "D", "D#", "E",  "F",
      "F#", "G",  "G#", "A", "A#", "B", "C"
    )
  }
}

object StringMapper {
  def apply(): StringMapper = new StringMapper()
}
