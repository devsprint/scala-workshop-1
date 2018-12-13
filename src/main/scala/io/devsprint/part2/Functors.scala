package io.devsprint.part2

object Functors {

  trait Functor[F[_]] {
    def map[A,B](a: F[A])(f: A => B): F[B]
  }


  object Functor {

    val listFunctor = new Functor[List] {
      override def map[A, B](a: List[A])(f: A => B): List[B] = a map f
    }

  }



}
