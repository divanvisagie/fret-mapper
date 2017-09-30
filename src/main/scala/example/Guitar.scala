package example

class Guitar(tuning: Array[String]) {

  val stringMapper = StringMapper()

  tuning.map { stringNote =>
    stringMapper.getStringStartingWith(stringNote)
  }

  def getFret(number: Int): Array[String] = {
    tuning
  }
}
object Guitar {
  def apply(tuning: Array[String]): Guitar = new Guitar(tuning)
}