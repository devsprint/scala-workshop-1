package io.devsprint.part2Parallel

object ParStep5 {

  trait Par[A] {

    def unit(a: A): Par[A]

    def map2[A,B,C](a: Par[A], b: Par[B])(f: (A, B) => C ): Par[C]

    def lazyUnit(a: => A): Par[A] = fork(unit(a))

    def fork(a: => Par[A]): Par[A]

    def run(a: Par[A]): A
  }

}
