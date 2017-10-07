package fretmapper.views

import fretmapper.core.Guitar
import fretmapper.views.ApplicationStore.ReceiveMessage

case class SelectedNote(note: String, string: Int)

class ApplicationStore extends Listener {

  private var listeners: Array[Listener] = Array()

  var selectedNotes = Array[SelectedNote]()

  override def receive: ReceiveMessage = {
    case l: Listener => listeners = listeners :+ l
    case g: Guitar => listeners.foreach { a =>
      a ! g
    }
    case sn: SelectedNote => {
      selectedNotes = selectedNotes :+ sn
      val toPrint = selectedNotes.mkString(",")
      println(s"Selected note $toPrint")
      listeners.foreach( _ ! selectedNotes)
    }
    case note: String => {
        println(s"Changing note to $note")
        listeners.foreach { a =>
          a ! note
        }
    }
    case _ => println("Got a message but not sure what to do")
  }
}

object ApplicationStore {
  type ReceiveMessage = PartialFunction[Any, Unit]

  def apply(): ApplicationStore = new ApplicationStore()
}
