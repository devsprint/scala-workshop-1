package io.devsprint.part2Parallel

object Sums extends App {

  def sum(ints: Seq[Int]): Int  = ints.foldLeft(0)(_ + _)

  def sum(ints: IndexedSeq[Int]): Int =
    if (ints.size <= 1)
      ints.headOption.getOrElse(0)
    else {
      val (l, r) = ints.splitAt(ints.length/2)
      sum(l) +sum(r)
    }


}
