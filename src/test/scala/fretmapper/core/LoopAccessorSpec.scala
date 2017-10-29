package fretmapper.core

import org.scalatest.{FlatSpec, Matchers}




class LoopAccessorSpec extends FlatSpec with Matchers {

  val CSequence = Seq("C","D","E","F","G","A","B")

  "given key of c sequence" should "return E when given index of 2" in {
    val answer = LoopAccessor[String](CSequence).getAtIndex(2)
    answer should be ("E")
  }

  "given key of c sequence" should "return C when given index of 7" in  {
    val answer = LoopAccessor[String](CSequence).getAtIndex(7)
    answer should be ("C")
  }

  "given key of c sequence" should "return C when given index of 14" in  {
    val answer = LoopAccessor[String](CSequence).getAtIndex(14)
    answer should be ("C")
  }

  "given key of c sequence" should "return B when given index of -1" in {
    val answer = LoopAccessor[String](CSequence).getAtIndex(-1)
    answer should be ("B")
  }

}
