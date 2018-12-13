package io.devsprint.part2

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Applicative extends App {

  val f = (x: Int) => (y: Int) => x + y
  val result = Future(42).map(f)
  //Future(10).apply(result)

}
