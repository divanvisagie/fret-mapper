package fretmapper.views

import fretmapper.FretMapperApp.applicationStore
import fretmapper.core.{ApplicationStore, ClearSelectedNotes, NoteMapper, ScaleMapper}

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


  val labelPadding = Insets(2,10,5,5)
  label.padding = labelPadding
  keyLabel.padding = labelPadding

  private val scaleSequenceComboBox = new ComboBox[String]()
  private val noteComboBox = new ComboBox[String]()

  NoteMapper.noteOrder.foreach { note =>
    noteComboBox += note
  }

  scaleSequenceComboBox += "None"
  ScaleMapper.scales.keySet.foreach { note =>
    scaleSequenceComboBox += note
  }
  scaleSequenceComboBox.onAction = handle {
    applicationStore ! scaleSequenceKey
    val keyText =
      NoteMapper.flattenKeyIfNeeded(
        NoteMapper.musicalKeys.getOrElse(scaleSequenceKey,Seq[String]()))
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
