package fretmapper.core

/**
  * SelectedNote represents a note that the user has selected
  * These notes are highlighted on the UI
  * */
case class SelectedNote(note: String, string: Int, fret: Int)
