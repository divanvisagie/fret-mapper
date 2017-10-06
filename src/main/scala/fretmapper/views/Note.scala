package fretmapper.views

import akka.actor.Actor.Receive
import fretmapper.core.{Guitar, NoteMapper}

import scalafx.scene.Node
import scalafx.scene.control._
import scalafx.scene.layout.BorderPane


/**
  * This class contains a view that is drawn at a certain point on the fretboard.
  * It is designed so that it does not need to be destroyed when it's values change
  *
  * The NoteView is self aware of it's place on the fretboard, therefore it's value is
  * changed by changing the value of it's guitar's tuning
  * */
class Note(stringNumber: Int, fretNumber: Int) extends StoreListener {
  private val label = new Label("")
  private var guitar: Guitar = Guitar.standardE

  private var highlightedNote = ""

  private val holder = new BorderPane()

  def container: Node = holder

  holder.setMinWidth(32)
  holder.center = label

  /**
    * Note that this view currently represents
    * */
  def note: String = guitar.getFret(fretNumber).lift(stringNumber).getOrElse("")

  def clearHighlights(): Unit = {
    if (fretNumber == 0) return
    style()
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



  private def fretStyle(str: String): String = {
    val fretStyleMap = Map[String,String](
      "Red" -> "-fx-background-color: red;",
      "White" -> "-fx-background-color: white;",
      "Blue" -> "-fx-background-color: cyan;"
    )


    if (fretNumber == 0) {
      s"${fretStyleMap(str)} fx-border-width: 0 1 0 0; -fx-border-color: transparent black transparent transparent;"
    } else {
      s"${fretStyleMap(str)} -fx-border-width: 0 2 1 0; -fx-border-color: white black black white;"
    }
  }


  private def style(): Unit = {
    if (fretNumber == 0) {
      holder.setStyle("")
    } else {
      holder.setStyle(fretStyle("White"))
    }
    NoteMapper.keys.getOrElse(highlightedNote, Seq("")).foreach { x =>
      if (x == note) {
        holder.setStyle(fretStyle("Blue"))
      }
    }
    if (highlightedNote == note) {
        holder.setStyle(fretStyle("Red"))
    }
    if (note == "") {
      holder.visible = false
    } else {
      holder.visible = true
    }
  }

  override def onChange: Receive = {
    case focusNote: String =>
      highlightedNote = focusNote
      style()
    case _=> noop()
  }
}
object Note {
  def apply(stringNumber: Int, fretNumber: Int): Note =
    new Note(stringNumber, fretNumber)
}
