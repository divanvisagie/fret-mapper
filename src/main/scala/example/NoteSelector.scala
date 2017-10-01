package example

import scalafx.Includes.handle
import scalafx.scene.control.ComboBox
import scalafx.scene.layout.HBox

class NoteSelector(noteViews: IndexedSeq[NoteView]) {
  val box = new HBox()
  private val comboBox = new ComboBox[String]()
  box.children.add(comboBox)
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
