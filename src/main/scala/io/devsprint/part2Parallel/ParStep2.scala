package io.devsprint.part2Parallel

import io.devsprint.part2Parallel.ParStep1.Par

object ParStep2 {

  // def map2[A,B](a: Par[A], f: Par[B])(f: (A, B) => C): Par[C]

//  def sum(ints: IndexedSeq[Int]): Par[Int] =
//    if (ints.size <= 1)
//      Par.unit(ints.headOption.getOrElse(0))
//    else {
//      val (l, r) = ints.splitAt(ints.length/2)
//      Par.map2(sum(l), sum(r))(_ + _)
//    }

}
