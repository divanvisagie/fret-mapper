package fretmapper.core

import fretmapper.core.ApplicationStore.ReceiveMessage



case object ClearSelectedNotes

class ApplicationStore extends Listener {

  private var listeners: Array[Listener] = Array()

  var selectedNotes: Array[SelectedNote] = Array[SelectedNote]()


  override def receive: ReceiveMessage = {
    case l: Listener => listeners = listeners :+ l
    case g: Guitar => listeners.foreach { a =>
      a ! g
    }
    case sn: SelectedNote =>
      if (selectedNotes.contains(sn)) {
        selectedNotes = selectedNotes.filterNot { x =>
          x.string == sn.string && x.note == sn.note
        }
      } else {
        selectedNotes = selectedNotes :+ sn
      }
      listeners.foreach(_ ! selectedNotes)
    case note: String =>
      listeners.foreach { a =>
        a ! note
      }
    case s: Scale =>
      listeners.foreach { a =>
        s.noteSequence.foreach { note =>
          println(note)
          a ! note
        }
        a ! s
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
