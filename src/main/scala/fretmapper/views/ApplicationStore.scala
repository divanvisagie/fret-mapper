package fretmapper.views

import fretmapper.core.Guitar
import fretmapper.views.ApplicationStore.ReceiveMessage


class ApplicationStore extends Listener {

  var listeners: Array[Listener] = Array()

  override def receive: ReceiveMessage = {
    case l: Listener => listeners = listeners :+ l
    case g: Guitar => listeners.foreach { a =>
      a ! g
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
