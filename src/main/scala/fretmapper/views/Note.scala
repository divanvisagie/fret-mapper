package fretmapper.views

import fretmapper.core.{Guitar, NoteMapper}

import scalafx.scene.control._
import scalafx.scene.layout.BorderPane


/**
  * This class contains a view that is drawn at a certain point on the fretboard.
  * It is designed so that it does not need to be destroyed when it's values change
  *
  * The NoteView is self aware of it's place on the fretboard, therefore it's value is
  * changed by changing the value of it's guitar's tuning
  * */
class Note(stringNumber: Int, fretNumber: Int) {
  private val label = new Label("")
  private var guitar: Guitar = Guitar.standardE

  private var highlightedNote = ""

  val holder = new BorderPane()
  if (fretNumber > 0) {
    holder.setStyle("-fx-background-color: white; -fx-border-width: 0 2 0 1; -fx-border-color: white black white black;")
  }
  holder.setStyle("-fx-border-width: 0 2 0 1; -fx-border-color: white black white black;")
  holder.setMinWidth(32)
  holder.center = label

  /**
    * Note that this view currently represents
    * */
  def note: String = guitar.getFret(fretNumber).lift(stringNumber).getOrElse("")

  def clearHighlights(): Unit = {
    if (fretNumber == 0) return
    holder.setStyle("-fx-background-color: white; -fx-border-width: 0 2 1 0; -fx-border-color: white black black white;")
  }

  /**
    * Change the tuning of the note in relation to the guitar it is on
    * */
  def changeTuning(g: Guitar): Unit = {
    clearHighlights()
    guitar = g
    val note = g.getFret(fretNumber).lift(stringNumber).getOrElse("")
    label.setText(s" $note ")
    style()
  }

  def columnPosition: Int = {
    val stringCount = guitar.strings.length
    stringCount - stringNumber
  }

  /**
    * Highlight this note if it is the same as the note it is being passed
    * */
  def highlight(note: String): Unit = {
    highlightedNote = note
    style()
  }

  private val fretStyle = Map[String,String](
    "Red" -> "-fx-background-color: red; -fx-border-width: 0 2 1 0; -fx-border-color: white black black white;",
    "White" -> "-fx-background-color: white; -fx-border-width: 0 2 1 0; -fx-border-color: white black black white;",
    "Blue" -> "-fx-background-color: cyan; -fx-border-width: 0 2 1 0; -fx-border-color: white black black white;"
  )


  def style(): Unit = {
    holder.setStyle(fretStyle("White"))
    NoteMapper.keys.getOrElse(highlightedNote, Seq("")).foreach { x =>
      if (x == note) {
        holder.setStyle(fretStyle("Blue"))
      }
    }
    if (highlightedNote == note) {
        holder.setStyle(fretStyle("Red"))
    }

  }
}
object Note {
  def apply(stringNumber: Int, fretNumber: Int): Note =
    new Note(stringNumber, fretNumber)
}
