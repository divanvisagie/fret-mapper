package fretmapper.core

import org.scalatest.{FlatSpec, Matchers}

class NoteMapperSpec extends FlatSpec with Matchers {

  def testNextNote(note: String, nextNote: String): Unit = {
    s"getNextNote $note" should s"return $nextNote" in {
      Note.next(note) should be (nextNote)
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

  def testPreviousNote(note: String, previousNote: String): Unit = {
    s"getPrevioustNote $note" should s"return $previousNote" in {
      Note.previous(note) should be (previousNote)
    }
  }
  testPreviousNote("A#", "A")
  testPreviousNote("E", "D#")

  def testFlattenSharpNote(note: String, flattened: String): Unit = {
    s"flattenSharpNote $note" should s"return $flattened" in {
      Note.flattenSharpNote(note) should be (flattened)
    }
  }
  testFlattenSharpNote("A#","B♭")
  testFlattenSharpNote("C#","D♭")
  testFlattenSharpNote("F#","G♭")
  testFlattenSharpNote("C","C")
}




