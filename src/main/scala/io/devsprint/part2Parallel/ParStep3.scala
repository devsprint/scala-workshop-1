package io.devsprint.part2Parallel

object ParStep3 {


  trait Par[A] {
    def unit(a: => A): Par[A]
    def get(a: Par[A]): A
    def fork(a: => Par[A]): Par[A]
  }


//  def sum(ints: IndexedSeq[Int]): Par[Int] =
//    if (ints.length <= 1)
//      Par.unit(ints.headOption.getOrElse(0))
//    else {
//      val (l,r) = ints.splitAt(ints.length/2)
//      Par.map2(Par.fork(sum(l)), Par.fork(sum(r)))(_ + _)
//    }




}
