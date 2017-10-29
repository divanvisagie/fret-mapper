package fretmapper.core

import scala.collection.immutable.ListMap


/**
  * Utilities for mapping notes
  * */
object NoteMapper {
  /**
    * Sequence representing the order of notes
    * */
  val noteOrder = Seq(
    "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G",  "G#"
  )
  val FLATCHARACTER = "♭"

  def flattenSharpNote(note: String): String = {


    if (!note.contains("#")) {
       note
    } else {
      next(note) + FLATCHARACTER
    }
  }

  def keyFromJumpSequence(root: String ,noteJumps: Seq[Int]): Seq[String] = {
      val rootIndex = noteOrder.indexOf(root)
      var currentIndex = rootIndex
      val noteOrderLoop = LoopAccessor[String](noteOrder)
      val notes = noteJumps.map { noteIndex =>
        currentIndex += noteIndex
        noteOrderLoop.getAtIndex(currentIndex)
      }
      (notes.last +: notes).reverse.tail.reverse
  }

  private val majorScale = Seq(2,2,1,2,2,2,1)
  private val naturalMinorScale = Seq(2,1,2,2,1,2,2)
  private val minorHarmonicScale = Seq(2,1,2,2,1,3,1)
  private val persianScale = Seq(1,3,1,1,2,3,1)


  /**
    * A map where the key is the note and the value is a sequence of notes that are contained within
    * that musical key
    * */
  val musicalKeys: ListMap[String, Seq[String]] = ListMap[String,Seq[String]](
    "A major" -> keyFromJumpSequence("A",majorScale),
    "A natural minor" -> keyFromJumpSequence("A",naturalMinorScale),
    "B flat major" -> keyFromJumpSequence("A#",majorScale).map(flattenSharpNote),
    "B major" -> keyFromJumpSequence("B",majorScale),
    "C major" -> keyFromJumpSequence("C",majorScale),
    "C persian" -> keyFromJumpSequence("C",persianScale).map(flattenSharpNote),
    "C natural minor" -> keyFromJumpSequence("C", naturalMinorScale),
    "C natural minor" -> keyFromJumpSequence("C",naturalMinorScale),
    "E natural minor" -> keyFromJumpSequence("E",naturalMinorScale),
    "F major" -> keyFromJumpSequence("F", majorScale),
    "F natural minor" -> keyFromJumpSequence("F", naturalMinorScale),
    "F minor harmonic" -> keyFromJumpSequence("F", minorHarmonicScale).map(flattenSharpNote),
    "G major" -> keyFromJumpSequence("G", majorScale),
  )

  private def getNote(note: String, selector: (Int) => Int): String = {
    val noteIndex = noteOrder.indexOf(note.toUpperCase)
    val noteOrderLoop = LoopAccessor[String](noteOrder)
    val nextIndex = if (noteIndex == noteOrder.length-1) {
      0
    } else {
      selector(noteIndex)
    }
    noteOrderLoop.getAtIndex(nextIndex)
  }

  /**
    * get the note that comes after the specified note
    * */
  def next(note: String): String = {
    getNote(note, {x => x + 1})
  }

  /**
    * ge the note that comes before the specified note
    * */
  def previous(note: String): String = {
    getNote(note, {x => x - 1})
  }
}

