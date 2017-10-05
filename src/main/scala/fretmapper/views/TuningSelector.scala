package fretmapper.views

import fretmapper.core.Guitar

import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.control.{ComboBox, Label}
import scalafx.scene.layout.HBox

/**
  * Represents a view that allows the user to select a guitar tuning
  * */
class TuningSelector(noteViews: IndexedSeq[NoteView]) {

  val box = new HBox()
  val label = new Label("Guitar Tuning:")
  label.padding = Insets(2,10,5,5)
  box.children.add(label)

  private val comboBox = new ComboBox[String]()
  Guitar.tuningsMap.keys.foreach { key =>
    comboBox += key
  }
  comboBox.getSelectionModel.select(0)
  box.children.add(comboBox)
  box.padding = Insets(0,0,10,0)

  comboBox.onAction = handle {
    val key = comboBox.getSelectionModel.getSelectedItem
    val guitar = Guitar(Guitar.tuningsMap(key))
    noteViews foreach(_.changeTuning(guitar))
  }
}

object TuningSelector {
  def apply(noteViews: IndexedSeq[NoteView]): TuningSelector =
    new TuningSelector(noteViews)
}
