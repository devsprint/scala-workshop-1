package io.devsprint.async.actors

import akka.actor.ActorSystem

object Boot {

  def main(args: Array[String]): Unit = {

    implicit val actorSystem: ActorSystem = ActorSystem("Demo")

    val frontend = actorSystem.actorOf(FactorialFrontend.props(10, false), "FactorialFrontend")
    frontend ! FactorialFrontend.Work


  }

}
