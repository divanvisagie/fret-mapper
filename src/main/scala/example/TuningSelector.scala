package example

import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.control.ComboBox
import scalafx.scene.layout.HBox

class TuningSelector(noteViews: IndexedSeq[NoteView]) {

  val box = new HBox()
  private val combobox = new ComboBox[String]()
  Guitar.tuningsMap.keys.foreach { key =>
    combobox += key
  }
  combobox.getSelectionModel.select(0)
  box.children.add(combobox)
  box.padding = Insets(0,0,10,0)

  combobox.onAction = handle {
    val key = combobox.getSelectionModel.getSelectedItem
    val guitar = Guitar(Guitar.tuningsMap(key))
    noteViews foreach(_.setGuitar(guitar))
  }
}

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
