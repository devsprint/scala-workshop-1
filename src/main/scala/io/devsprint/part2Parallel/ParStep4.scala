package io.devsprint.part2Parallel

object ParStep4 extends App {

  trait Par[A] {
    def unit(a: A): Par[A]
    def lazyUnit(a: => A): Par[A] = fork(unit(a))
    def get(a: Par[A]): A
    def fork(a: => Par[A]): Par[A]
  }

}
