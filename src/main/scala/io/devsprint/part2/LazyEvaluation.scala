package io.devsprint.part2

object LazyEvaluation extends App {


  lazy val x: Int = {
    println("test")
    42
  }

  //println(x)
  //println(x)

  def sideEffectCondition: Boolean ={
    println("test")
    true
  }

  def simpleCondition: Boolean = {
    false
  }
  lazy val lazyCondition = sideEffectCondition

  //println(if (simpleCondition && lazyCondition) "yes" else "no")

  def byNameMethod(n: => Int): Int =  {
    lazy val t = n
    t + t + t + 1
  }

  def retrieveMagicNumber: Int = {
    println("waiting")
    Thread.sleep(1000)
    42
  }

  println(byNameMethod(retrieveMagicNumber))
}
