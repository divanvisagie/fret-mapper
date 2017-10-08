package fretmapper.views

import fretmapper.core.Guitar
import fretmapper.views.ApplicationStore.ReceiveMessage

case class SelectedNote(note: String, string: Int)

case object ClearSelectedNotes

class ApplicationStore extends Listener {

  private var listeners: Array[Listener] = Array()

  var selectedNotes = Array[SelectedNote]()

  override def receive: ReceiveMessage = {
    case l: Listener => listeners = listeners :+ l
    case g: Guitar => listeners.foreach { a =>
      a ! g
    }
    case sn: SelectedNote => {
      if (selectedNotes.contains(sn)) {
        selectedNotes = selectedNotes.filterNot(_ == sn)
      } else {
        selectedNotes = selectedNotes :+ sn
      }
      listeners.foreach(_ ! selectedNotes)
    }
    case note: String => {
        listeners.foreach { a =>
          a ! note
        }
    }
    case ClearSelectedNotes =>
      selectedNotes = Array[SelectedNote]()
      listeners.foreach( _ ! selectedNotes)
    case _ => println("Got a message but not sure what to do")
  }
}

object ApplicationStore {
  type ReceiveMessage = PartialFunction[Any, Unit]

  def apply(): ApplicationStore = new ApplicationStore()
}
