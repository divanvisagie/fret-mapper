package example

class Guitar(tuning: Array[String]) {

  val stringMapper = StringMapper()

  val strings: Array[Array[String]] = tuning.map { stringNote =>
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
  def standardE: Guitar = new Guitar(Array("E","A","D","G","B","E"))
  def standardC: Guitar = new Guitar(Array("C","F","A#","D#","G","C"))

  val tuningsMap: Map[String,Array[String]] = Map[String,Array[String]](
    "Standard E" -> Array("E","A","D","G","B","E"),
    "Standard C" -> Array("C","F","A#","D#","G","C")
  )
}