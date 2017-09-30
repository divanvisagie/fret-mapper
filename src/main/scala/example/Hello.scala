package example

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button}
import scalafx.scene.layout.BorderPane

object Hello extends JFXApp {

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
    center = testButton
  }

  stage = new JFXApp.PrimaryStage {
    title.value = "Hello World"
    width = 300
    height = 200
    scene = new Scene {
      root = containerBox
    }
  }
}

