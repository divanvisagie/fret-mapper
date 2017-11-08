package fretmapper

import fretmapper.core.{ApplicationStore, Guitar}
import fretmapper.views._

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{BorderPane, GridPane}
import scalafx.Includes.handle

object FretMapperApp extends JFXApp {


  private val applicationStore = ApplicationStore()

  private val guitar = Guitar.standardE

  private val container = new GridPane()

  private val noteViews = (0 to Guitar.frets).flatMap { fret =>
    val holder = new BorderPane()
    val label = new Label(s"$fret")
    holder.center = label
    container.add(holder, fret, 0)
    guitar.getFret(fret).zipWithIndex.map { indexedNote =>
      val noteView = NoteView(applicationStore,indexedNote._2, fret)
      noteView.changeTuning(guitar)
      container.add(noteView.container, fret, noteView.columnPosition+1)
      applicationStore ! noteView
      noteView
    }
  }

  private val containerBox = new BorderPane {
    center = container
    top = TuningSelector(noteViews).box
    bottom = ScaleSelector(applicationStore).container
  }
  containerBox.padding = Insets(10, 10, 10, 10)
  applicationStore ! guitar

  stage = new JFXApp.PrimaryStage {
    title.value = "Fret Mapper"
    width = 780
    height = 270
    scene = new Scene {
      root = containerBox
    }
  }
}

