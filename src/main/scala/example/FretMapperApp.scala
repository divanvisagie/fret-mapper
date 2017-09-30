package example

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane, HBox}

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

object FretMapperApp extends JFXApp {

  private val guitar = Guitar.standardE

  private val container = new GridPane()

  private val noteViews = (0 to 12).flatMap { fret =>
    guitar.getFret(fret).zipWithIndex.map { indexedNote =>
      val noteView = NoteView(indexedNote._2, fret)
      noteView.setGuitar(guitar)
      container.add(noteView.holder, fret, noteView.columnPosition)
      noteView
    }
  }

  private val containerBox = new BorderPane {
    center = container
    top = new TuningSelector(noteViews).box
  }
  containerBox.padding = Insets(10,10,10,10)

  stage = new JFXApp.PrimaryStage {
    title.value = "Fret Mapper"
    width = 500
    height = 200
    scene = new Scene {
      root = containerBox
    }
  }
}

