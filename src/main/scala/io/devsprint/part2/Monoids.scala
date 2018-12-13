package io.devsprint.part2



object Monoids extends App {

  trait Monoid[A] {
    def zero: A
    def op(a1: A, a2: A): A
  }

  object Monoid {

    implicit object IntAdditionMonoid extends Monoid[Int] {
      override def zero: Int = 0

      override def op(a1: Int, a2: Int): Int = a1 + a2
    }

  }

  import Monoid.IntAdditionMonoid._
  println(List(1,2,3).foldLeft(zero)(op))


  object DefaultMonoids {
    implicit val stringConcatMonoid = new Monoid[String] {
      override def zero: String = ""

      override def op(a1: String, a2: String): String = s"$a1$a2"
    }
  }

  object Operations {
    def combineAll[A](list: List[A])(implicit monoid: Monoid[A]): A = {
      list.foldRight(monoid.zero)(monoid.op)
    }
  }

  import DefaultMonoids._
  println(Operations.combineAll(List("a", "b", "c")))


}
