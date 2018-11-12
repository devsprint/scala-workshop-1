package io.devsprint.async

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future, blocking}

object blockings {

  import scala.concurrent.ExecutionContext.Implicits.global

  private def blockingStuff(): Unit = {
    println("Blocking")
  }

  Future {
    blocking {
      blockingStuff()
    }
  }

}

object fakeblockings {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))

  private def blockingStuff(): Unit = {
    println("Blocking")
  }

  Future {
    blocking { // ???
      blockingStuff()
    }
  }

}
