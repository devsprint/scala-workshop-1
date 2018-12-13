package io.devsprint.part2

object TypeClassPart3 extends App {

  // 3.isPrime

  implicit class RichInt(val value: Int) extends AnyVal {
    def isEven: Boolean = value  %2 == 0
    def sqrt: Double = Math.sqrt(value)
  }

  println(3.isEven)


  //"6" /2

  implicit def stringToInt(value: String): Int = Integer.valueOf(value)
  println("6" /2)

}
