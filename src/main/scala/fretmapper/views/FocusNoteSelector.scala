package fretmapper.views

import fretmapper.FretMapperApp.applicationStore
import fretmapper.core.{ApplicationStore, ClearSelectedNotes, NoteMapper}

import scalafx.Includes.handle
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, ComboBox, Label}
import scalafx.scene.layout.HBox

/**
  * Creates a view that selects a focus note
  * */
class FocusNoteSelector(applicationStore: ApplicationStore) {
  private val hbox = new HBox()

  private val label = new Label("Highlight Note:")
  private val keyLabel = new Label("")

  val clearUserSelectedAButton = new Button("Clear Selection")
  clearUserSelectedAButton.onAction = handle {
    applicationStore ! ClearSelectedNotes
  }

  def container: Node = hbox

  def note: String = comboBox.getSelectionModel.getSelectedItem

  private def key: Seq[String] = NoteMapper.keys.getOrElse(note,Seq[String]())

  val labelPadding = Insets(2,10,5,5)
  label.padding = labelPadding
  keyLabel.padding = labelPadding

  private val comboBox = new ComboBox[String]()
  comboBox += "None"
  NoteMapper.noteOrder.foreach { note =>
    comboBox += note
  }
  comboBox.onAction = handle {
    println(note)
    applicationStore ! note
    val keyText =
      NoteMapper.keys.getOrElse(note,Seq[String]()).mkString(", ")

    keyLabel.setText(s"Key: $keyText")
  }
  comboBox.getSelectionModel.select(0)

  hbox.children.add(label)
  hbox.children.add(comboBox)
  hbox.children.add(keyLabel)
  hbox.children.add(clearUserSelectedAButton)

}

object FocusNoteSelector {
  def apply(applicationStore: ApplicationStore): FocusNoteSelector
  = new FocusNoteSelector(applicationStore)
}
