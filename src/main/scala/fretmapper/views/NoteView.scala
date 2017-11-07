package fretmapper.views

import fretmapper.core._
import fretmapper.core.ApplicationStore.ReceiveMessage

import scalafx.scene.Node
import scalafx.scene.control._
import scalafx.scene.layout.BorderPane
import scalafx.Includes.handle

/**
  * This class contains a view that is drawn at a certain point on the fretboard.
  * It is designed so that it does not need to be destroyed when it's values change
  *
  * The NoteView is self aware of it's place on the fretboard, therefore it's value is
  * changed by changing the value of it's guitar's tuning
  * */
class NoteView(applicationStore: ApplicationStore, stringNumber: Int, fretNumber: Int) extends Listener {
  private val label = new Label("")
  private var guitar: Guitar = Guitar.standardE

  private var highlightedNote = ""

  private val holder = new BorderPane()
  var scale: Scale = Scale("None",Seq[Int]())

  def container: Node = holder

  holder.setMinWidth(32)
  holder.center = label

  container.onMouseClicked = handle {
    val note = guitar.getFret(fretNumber).lift(stringNumber).getOrElse("")
    applicationStore ! SelectedNote(note, stringNumber, fretNumber)
  }
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
      "Cyan" -> "-fx-background-color: cyan;",
      "Green" -> "-fx-background-color: green;"
    )


    if (fretNumber == 0) {
      s"${fretStyleMap(str)} fx-border-width: 0 1 0 0; -fx-border-color: transparent black transparent transparent;"
    } else {
      s"${fretStyleMap(str)} -fx-border-width: 0 2 1 0; -fx-border-color: white black black white;"
    }
  }

//  private def scale(note: String): Seq[String] = {
//    Note.flattenKeyIfNeeded(
//      Note.musicalKeys.getOrElse(note, Seq())
//    )
//  }

  private def style(): Unit = {
    if (fretNumber == 0) {
      holder.setStyle("")
    } else {
      holder.setStyle(fretStyle("White"))
    }
//    if (scale(highlightedNote).mkString("").contains(Note.FLATCHARACTER)) {
//      label.setText(Note.flattenSharpNote(note))
//    }
    scale.noteSequence.foreach { x =>
      if (x == note || x == Note.flattenSharpNote(note)) {
        holder.setStyle(fretStyle("Cyan"))
      }
    }
    if (highlightedNote == note ||
      Note.flattenSharpNote(note) == Note.flattenSharpNote(highlightedNote)) {
        holder.setStyle(fretStyle("Red"))
    }

    applicationStore.selectedNotes.foreach { selectedNote =>
      if (selectedNote.string == stringNumber) {
        if (selectedNote.note == note) {
          holder.setStyle(fretStyle("Green"))
        }
      }
    }

    if (note == "") {
      holder.visible = false
    } else {
      holder.visible = true
    }
  }

  override def receive: ReceiveMessage = {
    case focusNote: String =>
      highlightedNote = focusNote
      changeTuning(guitar)
    case s: Scale =>
      scale = s
      style()
    case _: Array[SelectedNote] =>
      style()
    case _=> noop()
  }
}
object NoteView {
  def apply(applicationStore: ApplicationStore,stringNumber: Int, fretNumber: Int): NoteView =
    new NoteView(applicationStore,stringNumber, fretNumber)
}
