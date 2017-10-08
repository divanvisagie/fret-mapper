package fretmapper.core

import fretmapper.core.ApplicationStore.ReceiveMessage

trait Listener {
  def receive: ReceiveMessage

  def !(message: Any): Unit = receive(message)

  def noop(): Unit = {}
}
