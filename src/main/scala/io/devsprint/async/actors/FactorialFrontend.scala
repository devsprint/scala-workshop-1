package io.devsprint.async.actors

import akka.actor.{Actor, ActorLogging, Props, ReceiveTimeout}

import scala.concurrent.duration._

class FactorialFrontend(limit: Int, repeat: Boolean) extends Actor with ActorLogging {

  import FactorialFrontend._

  context.setReceiveTimeout(10.seconds)

  var results: Int = 0

  override def receive: Receive = {
    case Work =>
      sendJobs()
      context.become(startWorking)
  }


  def startWorking: Receive = {
    case (n: Int, factorial: BigInt) =>
      log.info("{}! = {}", n, factorial)
      results = results + 1
      if (results == limit) {
        log.debug("{}! = {}", n, factorial)
        if (repeat) sendJobs()
        else context.system.terminate()
      }
    case ReceiveTimeout =>
      log.info("Timeout")
      sendJobs()
      results = 0
  }

  def sendJobs() = {
    (1 to limit).foreach { number =>
      context.actorOf(FactorialBackend.props, s"FactorialBacked-$number") ! number
    }
  }

}

object FactorialFrontend {
  def props(limit: Int, repeat: Boolean) = Props(new FactorialFrontend(limit, repeat))

  case object Work

}

