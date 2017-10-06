package fretmapper.views

import fretmapper.views.ApplicationStore.ReceiveMessage

trait Listener {
  def receive: ReceiveMessage

  def !(message: Any): Unit = receive(message)

  def noop(): Unit = {}
}
