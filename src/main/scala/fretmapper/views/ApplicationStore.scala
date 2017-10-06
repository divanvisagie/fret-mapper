package fretmapper.views

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import fretmapper.core.Guitar

trait StoreListener {
  def onChange: Receive

  def !(message: Any): Unit = onChange(message)

  def noop(): Unit = {}
}

class ApplicationStore extends Actor with ActorLogging {

  var listeners: Array[StoreListener] = Array()



  override def receive: Receive = {
    case l: StoreListener => listeners = listeners :+ l
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
  def props(): Props = Props(new ApplicationStore)
}
