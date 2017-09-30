package example

import org.scalatest.{FlatSpec, Matchers}

class NoteMapperSpec extends FlatSpec with Matchers {
  val noteMapper = NoteMapper()

  def testNextNote(note: String, nextNote: String): Unit = {
    s"getNextNote $note" should s"return $nextNote" in {
      noteMapper.getNextNote(note) should be (nextNote)
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

class StringMapperSpec extends FlatSpec with Matchers {
  val stringMapper = StringMapper()

  def testForNote(note: String): Unit = {
    s"getStringStartingWith $note" should s"have a length of 12 and end with $note" in {
      val string = stringMapper getStringStartingWith note
      string.length should be (12)
      string.last should be (note)
    }
  }

  testForNote("A")
  testForNote("A#")
  testForNote("B")
  testForNote("C")
  testForNote("C#")
  testForNote("D")
  testForNote("D#")
  testForNote("E")
  testForNote("F")
  testForNote("F#")
  testForNote("G")
  testForNote("G#")
}
