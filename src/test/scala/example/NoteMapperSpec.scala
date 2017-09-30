package example

import org.scalatest.{FlatSpec, Matchers}

class NoteMapperSpec extends FlatSpec with Matchers {
  val noteMapper = NoteMapper
}

class StringMapperSpect extends FlatSpec with Matchers {
  val stringMapper = StringMapper()

  def testForNote(note: String): Unit = {
    s"getStringStartingWith $note" should s"have a length of 12 and end with $note" in {
      val string = stringMapper getStringStartingWith "C"
      string.length should be (12)
      string.last should be (note)
    }
  }

  testForNote("C")
}
