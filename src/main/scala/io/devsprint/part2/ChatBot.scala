package io.devsprint.part2

object ChatBot extends App {

  val reply: PartialFunction[String, String] = {
    case "Hello" => "Hello there!"
    case "Good bye" => "Bye Bye !"
  }

  val total = reply.orElse[String,String] {
    case _ => "I do not understand you!"
  }

  scala.io.Source.stdin.getLines().collect(total).foreach(println)

}
