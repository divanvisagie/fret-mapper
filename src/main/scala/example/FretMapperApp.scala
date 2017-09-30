package example

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane}



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

  val tuningSelector = new ComboBox[String]()
  Guitar.tuningsMap.keys.foreach { key =>
    tuningSelector += key
  }
  tuningSelector.getSelectionModel.select(0)

  tuningSelector.onAction = (_: ActionEvent) => {
    val key = tuningSelector.getSelectionModel.getSelectedItem
    val guitar = Guitar(Guitar.tuningsMap(key))
    noteViews foreach(_.setGuitar(guitar))
  }

  private val containerBox = new BorderPane {
    center = container
    top = tuningSelector
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

