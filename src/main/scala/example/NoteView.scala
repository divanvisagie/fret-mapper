package example

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane}
import scalafx.scene.shape.Rectangle


class NoteView(stringNumber: Int, fretNumber: Int) {
  private val label = new Label("")

  val holder = new BorderPane()
  holder.setStyle("-fx-background-color: white;")
  holder.setMinWidth(32)
  holder.center = label


  def setGuitar(guitar: Guitar): Unit = {
    val note = guitar.getFret(fretNumber)(stringNumber)
    label.setText(note)
  }
}
object NoteView {
  def apply(stringNumber: Int, fretNumber: Int): NoteView =
    new NoteView(stringNumber, fretNumber)
}
