package fretmapper.views

import fretmapper.core.{ApplicationStore, ClearSelectedNotes, Note, Scale}

import scalafx.Includes.handle
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, ComboBox, Label}
import scalafx.scene.layout.HBox

/**
  * Creates a view that selects a focus scale
  * */
class ScaleSelector(applicationStore: ApplicationStore) {
  private val hbox = new HBox()
  private val label = new Label("Highlight Scale:")
  private val keyLabel = new Label("")

  val clearUserSelectedAButton = new Button("Clear Selection")
  clearUserSelectedAButton.onAction = handle {
    applicationStore ! ClearSelectedNotes
  }

  def container: Node = hbox

  def scaleSequenceKey: String = scaleSequenceComboBox.getSelectionModel.getSelectedItem
  def note: String = noteComboBox.getSelectionModel.getSelectedItem

  private val boxPadding = Insets(0,0,0,10)
  private val labelPadding = Insets(2,10,5,5)
  label.padding = labelPadding
  keyLabel.padding = labelPadding


  private val scaleSequenceComboBox = new ComboBox[String]()
  private val noteComboBox = new ComboBox[String]()

  noteComboBox += "None"
  Note.noteOrder.foreach { note =>
    noteComboBox += note
  }
  noteComboBox.getSelectionModel.select(0)

  scaleSequenceComboBox.margin = boxPadding
  scaleSequenceComboBox += "None"
  Scale.scales.keySet.foreach { note =>
    scaleSequenceComboBox += note
  }
  scaleSequenceComboBox.onAction = handle {
    val scaleSequence = Scale.scales.getOrElse(scaleSequenceKey,Seq[Int]())
    val scale = Scale(note,scaleSequence)

    applicationStore ! scale

    val keyText = Note.keyFromJumpSequence(note, scaleSequence)
            .mkString(", ")

    keyLabel.setText(s" $keyText")
  }
  scaleSequenceComboBox.getSelectionModel.select(0)

  hbox.children.add(label)
  hbox.children.add(noteComboBox)
  hbox.children.add(scaleSequenceComboBox)
  hbox.children.add(keyLabel)
  hbox.children.add(clearUserSelectedAButton)

}

object ScaleSelector {
  def apply(applicationStore: ApplicationStore): ScaleSelector
  = new ScaleSelector(applicationStore)
}
