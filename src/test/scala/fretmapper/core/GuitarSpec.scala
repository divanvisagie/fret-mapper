package fretmapper.core

import org.scalatest.{FlatSpec, Matchers}

class GuitarSpec extends FlatSpec with Matchers {
  val standardETunedGuitar = Guitar(Array("E","A","D","G","B","E"))
  val standardCTunedGuitar = Guitar(Array("C","F","A#","D#","G","C"))

  "getFret 0 on a standard E tuned guitar" should "return (E,A,D,G,B,E)" in {
    standardETunedGuitar.getFret(0) should be (Seq("E","A","D","G","B","E"))
  }

  "getFret 0 on a standard C tuned guitar" should "return (C,F,A#,D#,G,C)" in {
    standardCTunedGuitar.getFret(0) should be (Seq("C","F","A#","D#","G","C"))
  }

  "getFret 3 on a standard E tuned guitar" should "return (G,C,F,A#,D,G)" in {
    standardETunedGuitar.getFret(3) should be (Seq("G","C","F","A#","D","G"))
  }

  "getFret 0 on an E tuned bass guitar" should "return (E,A,D,G)" in {
     val guitar = Guitar(Guitar.tuningsMap("Bass E"))
     guitar.getFret(0) should be (Seq("E","A","D","G"))
  }

}
