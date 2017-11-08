package fretmapper.core

import org.scalatest.{FlatSpec, Matchers}

class StringMapperSpec extends FlatSpec with Matchers {

  def testForNote(note: String): Unit = {
    s"getStringStartingWith $note" should s"""have a length of ${Guitar.frets} and end with $note""" in {
      val string = StringMapper getStringStartingWith note
      string.length should be (Guitar.frets)
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
