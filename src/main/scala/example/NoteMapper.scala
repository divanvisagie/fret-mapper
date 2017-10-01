package example


class NoteMapper {

  import NoteMapper._

  def getNote(note: String, selector: (Int) => Int): String = {
    val noteIndex = noteOrder.indexOf(note.toUpperCase)
    val nextIndex = if (noteIndex == noteOrder.length-1) {
      0
    } else {
      selector(noteIndex)
    }
    noteOrder(nextIndex)
  }

  def getNextNote(note: String): String = {
    getNote(note, {x => x + 1})
  }

}

object NoteMapper {
  def apply(): NoteMapper = new NoteMapper()
  val noteOrder = Array(
    "A", "A#", "B", "C", "C#", "D", "D#", "E",  "F", "F#", "G",  "G#"
  )

  val keys: Map[String, Array[String]] = Map[String,Array[String]](
    "A" -> Array("A", "B", "C#", "D", "E", "F#", "G#"),
    "A#" -> Array("A#" ,"C", "D", "D#", "F", "G", "A"),
    "B" -> Array("B", "C#", "D#", "E", "F#", "G#", "A#"),
    "C" -> Array("C", "D", "E", "F", "G", "A", "B", "C")
  )
}

