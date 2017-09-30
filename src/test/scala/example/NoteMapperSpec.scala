package example

import org.scalatest.{FlatSpec, Matchers}

class NoteMapperSpec extends FlatSpec with Matchers {
  val noteMapper = NoteMapper
}

class StringMapperSpect extends FlatSpec with Matchers {
  val stringMapper = StringMapper()

  "getStringStartingWith C" should " have a length of 12" in {
    val string = stringMapper getStringStartingWith "C"
    string.length should be (12)
  }
}
