package io.devsprint.part2

import cats.kernel.Monoid
import cats.implicits._

object MonoidCats extends App {

//  trait Semigroup[A] {
//    def combine(x: A, y: A): A
//  }
//
//  trait Monoid[A] extends Semigroup[A] {
//    def empty: A
//  }


  val result = Monoid[String].combineAll(List("a","b","c"))
  println(result)

  val scores = List(Map("Joe" -> 12), Map("Kate"  -> 21), Map("Joe" -> 10))
  val totals = Monoid[Map[String, Int]].combineAll(scores)

  println(totals)

  val op1 = Option(1)
  val op2 = Option(2)

  println( op1 |+| op2)

}
