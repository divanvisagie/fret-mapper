package example

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
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

  private val containerBox = new BorderPane {
    center = container
    top = new TuningSelector(noteViews).box
    bottom = new NoteSelector(noteViews).box
  }
  containerBox.padding = Insets(10,10,10,10)

  stage = new JFXApp.PrimaryStage {
    title.value = "Fret Mapper"
    width = 500
    height = 250
    scene = new Scene {
      root = containerBox
    }
  }
}

