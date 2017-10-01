package example

import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.control.{ComboBox, Label}
import scalafx.scene.layout.HBox

class TuningSelector(noteViews: IndexedSeq[NoteView]) {

  val box = new HBox()
  val label = new Label("Guitar Tuning:")
  label.padding = Insets(2,10,5,5)
  box.children.add(label)

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

object TuningSelector {
  def apply(noteViews: IndexedSeq[NoteView]): TuningSelector =
    new TuningSelector(noteViews)
}
