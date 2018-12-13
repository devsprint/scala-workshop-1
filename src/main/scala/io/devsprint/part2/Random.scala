package io.devsprint.part2

object Random extends App {

  val rng = new scala.util.Random

//  println(rng.nextDouble())
//  println(rng.nextDouble())
//
  def rollDie: Int = {
    val rng = new scala.util.Random
    rng.nextInt(6)
  }

  val rng2 = new SimpleRNG(42)
//  println(rng2.nextInt)
//  println(rng2.nextInt)

  val (n1, rng3) = rng2.nextInt
  val (n2, rng4) = rng3.nextInt

  println(n1)
  println(n2)


}


trait RNG {
  def nextInt: (Int, RNG)
}
class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = new SimpleRNG(newSeed)
    val n  = (newSeed >>> 16).toInt
    (n, nextRNG)
  }

}