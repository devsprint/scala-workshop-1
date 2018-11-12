package io.devsprint.async

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import connection._

/**
  * Blocking outside Future
  */
object outside {

  private def isProfitable(quote: Double): Boolean = quote < 4.0800

  def main(args: Array[String]): Unit = {
    val rateQuote = Future { getCurrentQuote(USD) }
    val amount = 2000.00
    val purchaseIfProfitable = rateQuote map { quote =>
      if (isProfitable(quote)) buy(amount, quote)
      else throw new Exception("Not profitable.")
    }

    val result = Await.result(purchaseIfProfitable, 0 nanos)
    println(result)
  }


}
