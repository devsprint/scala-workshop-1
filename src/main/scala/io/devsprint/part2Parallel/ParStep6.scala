package io.devsprint.part2Parallel

import scala.concurrent.duration.TimeUnit

object ParStep6 {

  trait Par[A] {

    def unit(a: A): Par[A]

    def map2[A,B,C](a: Par[A], b: Par[B])(f: (A, B) => C ): Par[C]

    def lazyUnit(a: => A): Par[A] = fork(unit(a))

    def fork(a: => Par[A]): Par[A]

    def run(a: Par[A]): A
  }

//  class ExecutorService {
//    def submit[A](a: Callable[A]): Future[A]
//  }

  trait Callable[A] {
    def call: A
  }

  trait Future[A] {
    def get: A
    def get(timeout: Long, unit: TimeUnit): A
    def cancel(evenIfRunning: Boolean): Boolean
    def isDone: Boolean
    def isCancelled: Boolean
  }


  // type Par[A] = ExecutorService => Future[A]
  // def run[A](s: ExecutorService)(a: Par[A]): Future[A] = a(s)

}
