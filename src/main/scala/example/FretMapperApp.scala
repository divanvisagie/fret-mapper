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

  val guitar = Guitar.standardE

  val strings = ObservableBuffer[Array[String]](
    guitar.strings(0),
    guitar.strings(1),
    guitar.strings(2)
  )

  val container = new GridPane()

  (1 to 12).foreach { fret =>
    guitar.getFret(fret).zipWithIndex.foreach { indexedNote =>
      val label = new Label(s"-${indexedNote._1}-")

      val holder = new BorderPane()
      holder.setStyle("-fx-background-color: white;")
      holder.setMinWidth(32)
      holder.center = label

      container.add(holder,fret,indexedNote._2)
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

