package io.devsprint.part2

object Monads extends App {

  trait MyTry[+ A] {
    def flatMap[B](f: A => MyTry[B]): MyTry[B]
  }

  object MyTry {
    def apply[A](value: => A): MyTry[A] =
      try {
        Success(value)
      } catch {
        case err: Throwable => Failure(err)
      }

  }

  case class Success[A](value: A) extends MyTry[A] {
    override def flatMap[B](f: A => MyTry[B]): MyTry[B] =
      try{
        f(value)
      } catch {
        case err: Throwable => Failure(err)
      }
  }

  case class Failure(err: Throwable) extends MyTry[Nothing] {
    override def flatMap[B](f: Nothing => MyTry[B]): MyTry[B] = this
  }



  val myTryInstance = MyTry {
    throw new RuntimeException("boom !")
  }

  println(myTryInstance)

  // left identity
  // unit(x).flatMap(f) == f(x)


}
