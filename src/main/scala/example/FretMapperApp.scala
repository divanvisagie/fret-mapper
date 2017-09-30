package example

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane}
import scalafx.scene.shape.Rectangle





object FretMapperApp extends JFXApp {

  private val guitar = Guitar.standardE

  val container = new GridPane()

  (0 to 12).foreach { fret =>
    guitar.getFret(fret).zipWithIndex.foreach { indexedNote =>

      val noteView = NoteView(indexedNote._2,fret)
      noteView.setGuitar(guitar)
      container.add(noteView.holder,fret,noteView.columnPosition)
    }

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
  }

  stage = new JFXApp.PrimaryStage {
    title.value = "Fret Mapper"
    width = 500
    height = 500
    scene = new Scene {
      root = containerBox
    }
  }
}

