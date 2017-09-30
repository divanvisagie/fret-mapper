package example

import org.scalatest.{FlatSpec, Matchers}

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
