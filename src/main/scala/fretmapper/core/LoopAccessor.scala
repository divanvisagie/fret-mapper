package fretmapper.core

/**
  * Wraps an array to access at an infinite index level, if the index you requested
  * is passed the size of the array, it loops back to the beginning
  * */
class LoopAccessor[T](array: Array[T]) {
  def getAtIndex(index: Int): T = {

    if (index >= array.length) {
      val remainder = index % array.length
      return array(remainder)
    }
  array(index)
  }
}

object LoopAccessor {
  def apply[T](array: Array[T]): LoopAccessor[T] = new LoopAccessor(array)
}
