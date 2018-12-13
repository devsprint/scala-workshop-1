package io.devsprint.part2

import cats.effect.{ExitCode, IO, IOApp}

import scala.io.StdIn

object Effects extends IOApp {



  def PrintLn(value: String): IO[Unit] = IO {
    println(value)
  }

  def ReadLn: IO[String] = IO {
    StdIn.readLine()
  }

  private def fahrenheitToCelsius(f: Double) : Double =
    (f -32) * 5.0 /9.0

  override def run(args: List[String]): IO[ExitCode] = {

    for {
      _  <- PrintLn("Enter a temperature in degrees Fahrenheit:")
      d <- ReadLn.map(_.toDouble)
      _  <- PrintLn("Temperature in degrees Celsius:")
      _ <- PrintLn(fahrenheitToCelsius(d).toString)

    } yield ExitCode.Success
  }
}
