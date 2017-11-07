package fretmapper.core

import scala.collection.immutable.ListMap

/**
  * Represent a scale sequence and it's root note
  * */
class Scale(note: String, scaleSequence: Seq[Int]) {
  def noteSequence: Seq[String] =
    if (note == "None" || scaleSequence.isEmpty){
      Seq[String]() }
    else {
      Note.keyFromJumpSequence(note,scaleSequence)
    }
}


object Scale {

  def apply(note: String, scaleSequence: Seq[Int]): Scale = new Scale(note, scaleSequence)

  private val majorScale = Seq(2,2,1,2,2,2,1)
  private val naturalMinorScale = Seq(2,1,2,2,1,2,2)
  private val minorHarmonicScale = Seq(2,1,2,2,1,3,1)
  private val diminished = Seq(2,1,2,1,2,1,2,1)
  private val persianScale = Seq(1,3,1,1,2,3,1)
  private val majorPentatonic = Seq(2,2,3,2,3)
  private val minorPentatonic = Seq(3,2,2,3,2)

  val scales: ListMap[String, Seq[Int]] = ListMap[String, Seq[Int]](
    "Major" -> majorScale,
    "Natural minor" -> naturalMinorScale,
    "Minor pentatonic" -> minorPentatonic,
    "Major pentatonic" -> majorPentatonic,
    "Minor harmonic scale" -> minorHarmonicScale,
    "Diminished" -> diminished,
    "Persian" -> persianScale
  )
}
