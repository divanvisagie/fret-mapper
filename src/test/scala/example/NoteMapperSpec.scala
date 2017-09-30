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

class GuitarSpec extends FlatSpec with Matchers {
  val standardETunedGuitar = Guitar(Array("E","A","D","G","B","E"))
  val standardCTunedGuitar = Guitar(Array("C","F","A#","D#","G","C"))

  "getFret 0 on a standard E tuned guitar" should "return (E,A,D,G,B,E)" in {
    standardETunedGuitar.getFret(0) should be (List("E","A","D","G","B","E"))
  }

  "getFret 0 on a standard C tuned guitar" should "return (C,F,A#,D#,G,C)" in {
    standardCTunedGuitar.getFret(0) should be (List("C","F","A#","D#","G","C"))
  }

  "getFret 3 on a standard E tuned guitar" should "return (G,C,F,A#,D,G)" in {
    standardETunedGuitar.getFret(3) should be (List("G","C","F","A#","D","G"))
  }

}


