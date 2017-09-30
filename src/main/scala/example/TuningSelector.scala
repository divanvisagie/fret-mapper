package example

import scalafx.event.ActionEvent
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

  combobox.onAction = (_: ActionEvent) => {
    val key = combobox.getSelectionModel.getSelectedItem
    val guitar = Guitar(Guitar.tuningsMap(key))
    noteViews foreach(_.setGuitar(guitar))
  }
}
