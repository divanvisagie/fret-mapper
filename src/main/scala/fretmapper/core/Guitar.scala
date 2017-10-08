package fretmapper.core

class Guitar(tuning: Seq[String]) {


  /**
    * Contains a Sequence representing guitar strings which contains another
    * sequence of Strings the index representing the fret number and the value
    * representing the note
    * */
  val strings: Seq[Seq[String]] = tuning.map { stringNote =>
    StringMapper.getStringStartingWith(stringNote).toSeq
  }

  /**
    * Gets the tuning for each string on a fret of the guitar as a Sequence of
    * notes
    * */
  def getFret(number: Int): Seq[String] = {
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
  def apply(tuning: Seq[String]): Guitar = new Guitar(tuning)

  /**
    * Creates a new 6 string guitar with standard E tuning
    * */
  def standardE: Guitar = new Guitar(Array("E","A","D","G","B","E"))


  /**
    * Creates a new 6 string guitar with standard C tuning
    * */
  def standardC: Guitar = new Guitar(Array("C","F","A#","D#","G","C"))

  /**
    * A map key value store where key is the Guitar tuning and the value is an
    * array containing the notes it is tuned to
    * */
  val tuningsMap: Map[String,Seq[String]] = Map[String,Seq[String]](
    "Standard E" -> Seq("E","A","D","G","B","E"),
    "Standard C" -> Seq("C","F","A#","D#","G","C"),
    "Drop C" -> Seq("C","G","C","F","A","D"),
    "Bass E" -> Seq("E","A","D","G")
  )
}