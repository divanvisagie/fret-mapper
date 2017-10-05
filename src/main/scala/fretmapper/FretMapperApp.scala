package fretmapper

import fretmapper.core.Guitar
import fretmapper.views.{FocusNoteSelector, Note, TuningSelector}

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.{BorderPane, GridPane}


object FretMapperApp extends JFXApp {

  private val guitar = Guitar.standardE

  private val container = new GridPane()

  private val noteViews = (0 to 12).flatMap { fret =>
    val holder = new BorderPane()
    holder.setMinWidth(32)
    val label = new Label(s"$fret")
    holder.center = label
    container.add(holder, fret, 0)
    guitar.getFret(fret).zipWithIndex.map { indexedNote =>
      val noteView = Note(indexedNote._2, fret)
      noteView.changeTuning(guitar)
      container.add(noteView.container, fret, noteView.columnPosition+1)
      noteView
    }
  }

  private val containerBox = new BorderPane {
    center = container
    top = TuningSelector(noteViews).box
    bottom = FocusNoteSelector(noteViews).container
  }
  containerBox.padding = Insets(10, 10, 10, 10)

  stage = new JFXApp.PrimaryStage {
    title.value = "Fret Mapper"
    width = 500
    height = 250
    scene = new Scene {
      root = containerBox
    }
  }
}

