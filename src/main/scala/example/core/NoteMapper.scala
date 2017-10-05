package example.core


/**
  * Utilities for mapping notes
  * */
object NoteMapper {
  /**
    * Sequence representing the order of notes
    * */
  val noteOrder = Seq(
    "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G",  "G#"
  )

  /**
    * A map where the key is the note and the value is a sequence of notes that are contained within
    * that musical key
    * */
  val keys: Map[String, Seq[String]] = Map[String,Seq[String]](
    "A" -> Seq("A", "B", "C#", "D", "E", "F#", "G#"),
    "A#" -> Seq("A#" ,"C", "D", "D#", "F", "G", "A"),
    "B" -> Seq("B", "C#", "D#", "E", "F#", "G#", "A#"),
    "C" -> Seq("C", "D", "E", "F", "G", "A", "B", "C"),
    "C#" -> Seq("C#", "D#", "E#", "F#", "G#", "A#", "B#", "C#"),
    "D" -> Seq("D", "E", "F#", "G", "A", "B", "C#"),
    "D#" -> Seq("D#", "F" ,"G", "G#" ,"A#" ,"C", "D"),
    "E" -> Seq("E", "F#", "G#", "A", "B", "C#", "D#"),
    "F" -> Seq("F","G", "A", "A#", "C", "D", "E"),
    "F#" -> Seq("F#", "G#", "A#", "B", "C#", "D#", "E#"),
    "G" -> Seq("G", "A", "B", "C" ,"D" ,"E", "F#"),
    "G#" -> Seq("G#", "A#", "C" ,"C#", "D#", "F", "G")
  )

  private def getNote(note: String, selector: (Int) => Int): String = {
    val noteIndex = noteOrder.indexOf(note.toUpperCase)
    val nextIndex = if (noteIndex == noteOrder.length-1) {
      0
    } else {
      selector(noteIndex)
    }
    noteOrder(nextIndex)
  }

  /**
    * get the note that comes after the specified note
    * */
  def next(note: String): String = {
    getNote(note, {x => x + 1})
  }
}

