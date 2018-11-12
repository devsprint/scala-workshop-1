package io.devsprint.async.actors

import akka.actor.{Actor, ActorLogging, Props}
import akka.pattern.pipe
import scala.annotation.tailrec
import scala.concurrent.Future

class FactorialBackend extends Actor with ActorLogging{

  import context.dispatcher


  override def receive: Receive = {
    case (n: Int) =>
      log.info(s"$self  calculates $n!")
      Future(factorial(n)) map { result => (n, result) } pipeTo sender()
  }

  def factorial(n: Int): BigInt = {
    @tailrec def factorialAcc(acc: BigInt, n: Int): BigInt = {
      if (n <= 1) acc
      else factorialAcc(acc * n, n - 1)
    }
    factorialAcc(BigInt(1), n)
  }
}

object FactorialBackend {
  def props = Props(new FactorialBackend)
}

