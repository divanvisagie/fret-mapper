package example

import scalafx.Includes.handle
import scalafx.geometry.Insets
import scalafx.scene.control.{CheckBox, ComboBox, Label}
import scalafx.scene.layout.HBox

class NoteSelector(noteViews: IndexedSeq[NoteView]) {
  val box = new HBox()
  val label = new Label("Highlight Note:")
  val keyLabel = new Label("")
  val checkbox = new CheckBox("Highlight Key")

  def note: String = comboBox.getSelectionModel.getSelectedItem

  def key: Seq[String] = NoteMapper.keys.getOrElse(note,Seq[String]())

  checkbox.onAction = handle {
    if(checkbox.selected.value) {
      noteViews.foreach { nv =>
        nv.highlightKey(key)
      }
    }
  }

  val labelPadding = Insets(2,10,5,5)
  label.padding = labelPadding
  keyLabel.padding = labelPadding
  checkbox.padding = Insets(3,5,3,3)

  private val comboBox = new ComboBox[String]()
  comboBox += "None"
  NoteMapper.noteOrder.foreach { note =>
    comboBox += note
  }
  comboBox.onAction = handle {
    noteViews.foreach { noteView =>
      noteView.highlight(note)
    }
    val keyText =
      NoteMapper.keys.getOrElse(note,Seq[String]()).mkString(", ")

    keyLabel.setText(keyText)
    checkbox.selected = false
  }
  comboBox.getSelectionModel.select(0)

  box.children.add(label)
  box.children.add(comboBox)
  box.children.add(keyLabel)
  box.children.add(checkbox)
}

object NoteSelector {
  def apply(noteViews: IndexedSeq[NoteView]): NoteSelector =
    new NoteSelector(noteViews)
}
