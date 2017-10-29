package fretmapper.core

import org.scalatest.{FlatSpec, Matchers}




class LoopAccessorSpec extends FlatSpec with Matchers {

  val testArray = Array("C","D","E","F","G","A","B")

  "given key of c array" should "return E when given index of 2" in {
    val answer = LoopAccessor[String](testArray).getAtIndex(2)
    answer should be ("E")
  }

  "given key of c array" should "return C when given index of 7" in  {
    val answer = LoopAccessor[String](testArray).getAtIndex(7)
    answer should be ("C")
  }

  "given key of c array" should "return C when given index of 14" in  {
    val answer = LoopAccessor[String](testArray).getAtIndex(14)
    answer should be ("C")

  }

}
