package example


class NoteMapper {

  val noteOrder = Array(
    "C#", "D", "D#", "E",  "F",
    "F#", "G",  "G#", "A", "A#", "B", "C"
  )

  def getNextNote(note: String): String = {
    val noteIndex = noteOrder.indexOf(note.toUpperCase)

    val nextIndex = if (noteIndex == noteOrder.length-1) {
      0
    } else {
      noteIndex + 1
    }

    noteOrder(nextIndex)
  }
}

object NoteMapper {
  def apply(): NoteMapper = new NoteMapper()
}

