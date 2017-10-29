package fretmapper.views

import fretmapper.FretMapperApp.applicationStore
import fretmapper.core.{ApplicationStore, ClearSelectedNotes, NoteMapper}

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

  def scale: String = comboBox.getSelectionModel.getSelectedItem

//  private def key: Seq[String] = NoteMapper.musicalKeys.getOrElse(scale,Seq[String]())

  val labelPadding = Insets(2,10,5,5)
  label.padding = labelPadding
  keyLabel.padding = labelPadding

  private val comboBox = new ComboBox[String]()
  comboBox += "None"
  NoteMapper.musicalKeys.keySet.foreach { note =>
    comboBox += note
  }
  comboBox.onAction = handle {
//    val noteToUse = NoteMapper.musicalKeys(note).head
//    println(noteToUse)
    applicationStore ! scale
    val keyText =
      NoteMapper.flattenKeyIfNeeded(
        NoteMapper.musicalKeys.getOrElse(scale,Seq[String]()))
        .mkString(", ")

    keyLabel.setText(s" $keyText")
  }
  comboBox.getSelectionModel.select(0)

  hbox.children.add(label)
  hbox.children.add(comboBox)
  hbox.children.add(keyLabel)
  hbox.children.add(clearUserSelectedAButton)

}

object ScaleSelector {
  def apply(applicationStore: ApplicationStore): ScaleSelector
  = new ScaleSelector(applicationStore)
}
