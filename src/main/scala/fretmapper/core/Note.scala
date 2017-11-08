package fretmapper.core

import scala.collection.immutable.ListMap


/**
  * Utilities for mapping notes
  * */
object Note {
  /**
    * Sequence representing the order of notes
    * */
  val noteOrder = Seq(
    "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G",  "G#"
  )
  val FLATCHARACTER = "♭"

  /**
    * Turns a sharp note into it's flat equivalent, or does nothing when
    * a note is not sharp
    * */
  def flattenSharpNote(note: String): String = {


    if (!note.contains("#")) {
       note
    } else {
      next(note) + FLATCHARACTER
    }
  }

  def scaleFromJumpSequence(root: String, noteJumps: Seq[Int]): Seq[String] = {
      if (noteJumps.isEmpty) {
        return Seq[String]()
      }
      val rootIndex = noteOrder.indexOf(root)
      var currentIndex = rootIndex
      val noteOrderLoop = LoopAccessor[String](noteOrder)
      val notes = noteJumps.map { noteIndex =>
        currentIndex += noteIndex
        noteOrderLoop.getAtIndex(currentIndex)
      }
      (notes.last +: notes).reverse.tail.reverse
  }

  def flattenKeyIfNeeded(items: Seq[String]): Seq[String] = {
    if (items.length < 1) return items
    if (items.map(_.head).toSet.size != items.size) {
      items.map(flattenSharpNote)
    } else {
      items
    }
  }

    "D natural minor" -> keyFromJumpSequence("D",naturalMinorScale),
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

