package fretmapper

import fretmapper.core.Guitar
import fretmapper.views.{ApplicationStore, FocusNoteSelector, Note, TuningSelector}

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.{BorderPane, GridPane}
import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

object FretMapperApp extends JFXApp {

  val system = ActorSystem("guitar-app-state")

  private val applicationStore = system.actorOf(ApplicationStore.props())

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
      applicationStore ! noteView
      noteView
    }
  }

  private val containerBox = new BorderPane {
    center = container
    top = TuningSelector(noteViews).box
    bottom = FocusNoteSelector(applicationStore).container
  }
  containerBox.padding = Insets(10, 10, 10, 10)
  applicationStore ! guitar

  stage = new JFXApp.PrimaryStage {
    title.value = "Fret Mapper"
    width = 500
    height = 270
    scene = new Scene {
      root = containerBox
    }
  }
}

