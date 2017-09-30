package example

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane}
import scalafx.scene.shape.Rectangle





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

  noteViews.foreach(_.setGuitar(Guitar.standardC))

  val tuningSelector = new ComboBox[String]()
  tuningSelector += "Standard E"
  tuningSelector += "Standard C"

  tuningSelector.onAction = (_: ActionEvent) => {
    val newGuitarType = tuningSelector.getSelectionModel.getSelectedItem match {
      case "Standard C" => Guitar.standardC
      case _ => Guitar.standardE
    }
    noteViews foreach(_.setGuitar(newGuitarType))
  }

  val testButton = new Button("Say Hello")
  testButton.onAction = (_: ActionEvent) => {
    println("Test this")

    val alert = new Alert(AlertType.Information)
    alert.title = "Hello World Info"
    alert.headerText = "This is a hello world announcement"
    alert.contentText = "Hello World"
    alert.show()
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

