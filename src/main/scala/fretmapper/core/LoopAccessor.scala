package fretmapper.core

/**
  * Wraps a Sequence to access at an infinite index level, if the index you requested
  * is passed the size of the sequence, it loops back to the beginning
  * */
class LoopAccessor[T](seq: Seq[T]) {
  def getAtIndex(index: Int): T = {

    if (index < 0) {
      val indexToUse = seq.length + index
      return seq(indexToUse)
    }

    if (index >= seq.length) {
      val remainder = index % seq.length
      return seq(remainder)
    }
    seq(index)
  }
}

object LoopAccessor {
  def apply[T](seq: Seq[T]): LoopAccessor[T] = new LoopAccessor(seq)
}

