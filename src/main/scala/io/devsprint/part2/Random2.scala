package io.devsprint.part2

object Random2 extends App {

  type RAND[+A] = RNG => (A, RNG)

  val int: RAND[Int] = _.nextInt

  def unit[A](a: A): RAND[A] =
    rng => (a, rng)

  def map[A,B](s: RAND[A])(f: A => B): RAND[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }


  def map2[A,B, C](a: RAND[A], b: RAND[B])(f: (A, B) => C): RAND[C] =
    rng => {
      val (s, rng1) = a(rng)
      val (s2, rng2) = b(rng1)
      (f(s,s2), rng2)
    }

  def flatMap[A,B](f: RAND[A])(g: A => RAND[B]): RAND[B] =
    rng => {
      val (a, r1) = f(rng)
      g(a)(r1)
    }


  def nonNegativeInt(rng: RNG): (Double, RNG) = {
    val (i, r) = rng.nextInt
    (if (i<0) -(i+1) else i, r)
  }


  def nonNegativeEven: RAND[Int] =
    map(nonNegativeInt) (i => (i - i % 2).toInt)

  def nonNegativeLessThan(n: Int): RAND[Int] = {
    flatMap(nonNegativeInt) { i =>
      val mod: Int = (i % n).toInt
      if (i + (n-1) - mod >= 0) unit(mod) else nonNegativeLessThan(n)
    }
  }

  println(nonNegativeEven(new SimpleRNG(42))._1)

  println(nonNegativeLessThan(10)(new SimpleRNG(122))._1)

}
