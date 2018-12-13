package io.devsprint.part2

object LowerTypeBounds extends App {

  abstract class List[+T] { self =>
    def contains[T1 >: T](elem: T1): Boolean = self match {
      case Cons(x, xs) if x == elem => true
      case Cons(x, xs) => xs.contains(elem)
      case Nil => false
    }

  }

  case object Nil extends List[Nothing]
  case class Cons[+T](head: T, tail: List[T]) extends List[T]

}



// contains is Function1[-A, +B]