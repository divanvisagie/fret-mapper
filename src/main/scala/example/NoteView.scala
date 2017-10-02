package example

import scalafx.scene.control._
import scalafx.scene.layout.BorderPane


class NoteView(stringNumber: Int, fretNumber: Int) {
  private val label = new Label("")
  var _guitar: Guitar = Guitar.standardE

  val holder = new BorderPane()
  if (fretNumber > 0) {
    holder.setStyle("-fx-background-color: white; -fx-border-width: 0 2 0 1; -fx-border-color: white black white black;")
  }
  holder.setStyle("-fx-border-width: 0 2 0 1; -fx-border-color: white black white black;")
  holder.setMinWidth(32)
  holder.center = label

  def currentNote: String = _guitar.getFret(fretNumber)(stringNumber)

  def clearHighlights(): Unit = {
    if (fretNumber == 0) return
    holder.setStyle("-fx-background-color: white; -fx-border-width: 0 2 1 0; -fx-border-color: white black black white;")
  }

  def setGuitar(guitar: Guitar): Unit = {
    clearHighlights()
    _guitar = guitar
    val note = guitar.getFret(fretNumber).lift(stringNumber).getOrElse("")
    label.setText(s" $note ")
  }

  def columnPosition: Int = {
    val stringCount = _guitar.strings.length
    stringCount - stringNumber
  }

  def highlight(note: String): Unit = {
    if (fretNumber == 0) return
    if (note == currentNote) {
      holder.setStyle("-fx-background-color: red;")
    } else {
      holder.setStyle("-fx-background-color: white; -fx-border-width: 0 2 1 0; -fx-border-color: white black black white;")
    }
  }

  def highlightKey(key: Seq[String]): Unit = {
    if (fretNumber == 0) return

    key.foreach { note =>
      if (note == currentNote) {
        holder.setStyle("-fx-background-color: cyan;")
      }
    }
  }
}
object NoteView {
  def apply(stringNumber: Int, fretNumber: Int): NoteView =
    new NoteView(stringNumber, fretNumber)
}
