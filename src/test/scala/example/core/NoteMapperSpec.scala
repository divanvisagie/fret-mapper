package example.core

import org.scalatest.{FlatSpec, Matchers}

class NoteMapperSpec extends FlatSpec with Matchers {

  def testNextNote(note: String, nextNote: String): Unit = {
    s"getNextNote $note" should s"return $nextNote" in {
      NoteMapper.next(note) should be (nextNote)
    }
  }
  testNextNote("A","A#")
  testNextNote("A#","B")
  testNextNote("B","C")
  testNextNote("C","C#")
  testNextNote("C#","D")
  testNextNote("D#","E")
  testNextNote("E", "F")
  testNextNote("G", "G#")
  testNextNote("G#","A")
}




