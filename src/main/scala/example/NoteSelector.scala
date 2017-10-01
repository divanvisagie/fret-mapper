package example

import scalafx.Includes.handle
import scalafx.geometry.Insets
import scalafx.scene.control.ComboBox
import scalafx.scene.layout.HBox
import scalafx.scene.control.Label

class NoteSelector(noteViews: IndexedSeq[NoteView]) {
  val box = new HBox()
  val label = new Label("Highlight Note:")
  label.padding = Insets(2,10,5,5)
  box.children.add(label)
  private val comboBox = new ComboBox[String]()
  box.children.add(comboBox)
  comboBox += "None"
  NoteMapper.noteOrder.foreach { note =>
    comboBox += note
  }
  comboBox.onAction = handle {
    val note = comboBox.getSelectionModel.getSelectedItem
    noteViews.foreach { noteView =>
      noteView.highlight(note)
    }
  }

  comboBox.getSelectionModel.select(0)
}

object NoteSelector {
  def apply(noteViews: IndexedSeq[NoteView]): NoteSelector =
    new NoteSelector(noteViews)
}
