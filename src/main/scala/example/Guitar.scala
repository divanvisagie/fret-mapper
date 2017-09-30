package example

class Guitar(tuning: Array[String]) {

  val stringMapper = StringMapper()

  val strings = tuning.map { stringNote =>
    stringMapper.getStringStartingWith(stringNote)
  }

  def getFret(number: Int): Array[String] = {
    if (number == 0) {
      tuning
    } else {
      strings.map { guitarString =>
        guitarString(number-1)
      }
    }
  }
}
object Guitar {
  def apply(tuning: Array[String]): Guitar = new Guitar(tuning)
}