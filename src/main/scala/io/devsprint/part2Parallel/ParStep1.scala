package io.devsprint.part2Parallel



object ParStep1 {

  trait Par[A] {
    def unit(a: => A): Par[A]
    def get(a: Par[A]): A
  }



//  def sum(ints: IndexedSeq[Int]): Int =
//    if (ints.size <= 1)
//      ints.headOption.getOrElse(0)
//    else {
//      val (l, r) = ints.splitAt(ints.length/2)
//      val sumL: Par[Int] = Par.unit(sum(l))
//      val sumR: Par[Int] = Par.unit(sum(r))
//
//      Par.get(sumL) + Par.get(sumR)
//    }


}
