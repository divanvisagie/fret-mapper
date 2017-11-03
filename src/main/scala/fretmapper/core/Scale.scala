package fretmapper.core

import scala.collection.immutable.ListMap

/**
  * Represent a scale sequence and it's root note
  * */
case class Scale(note: String, scaleSequence: Seq[Int])



object ScaleMapper {
  private val majorScale = Seq(2,2,1,2,2,2,1)
  private val naturalMinorScale = Seq(2,1,2,2,1,2,2)
  private val minorHarmonicScale = Seq(2,1,2,2,1,3,1)
  private val diminished = Seq(2,1,2,1,2,1,2,1)
  private val persianScale = Seq(1,3,1,1,2,3,1)

  val scales: ListMap[String, Seq[Int]] = ListMap[String, Seq[Int]](
    "Major" -> majorScale,
    "Natural minor" -> naturalMinorScale,
    "Minor harmonic scale" -> minorHarmonicScale,
    "Diminished" -> diminished,
    "Persian" -> persianScale
  )
}
