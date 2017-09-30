package example

import scalafx.scene.control._
import scalafx.scene.layout.BorderPane


class NoteView(stringNumber: Int, fretNumber: Int) {
  private val label = new Label("")
  var _guitar: Guitar = Guitar.standardE

  val holder = new BorderPane()
  if (fretNumber > 0) {
    holder.setStyle("-fx-background-color: white;")
  }
  holder.setMinWidth(32)
  holder.center = label


  def setGuitar(guitar: Guitar): Unit = {
    _guitar = guitar
    val note = guitar.getFret(fretNumber)(stringNumber)
    label.setText(note)
  }

  def columnPosition: Int = {
    val stringCount = _guitar.strings.length
    stringCount - stringNumber
  }
}
object NoteView {
  def apply(stringNumber: Int, fretNumber: Int): NoteView =
    new NoteView(stringNumber, fretNumber)
}
