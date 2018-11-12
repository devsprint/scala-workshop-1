package io.devsprint.error.handling.thetry

import java.io.FileNotFoundException
import java.net.{MalformedURLException, URL}

import scala.io.Source
import scala.util.Try


object tries {

  // handling errors, java way
  case class Customer(age: Int)
  class Beer
  case class UnderAgeException(message: String) extends Exception(message)

  def buyBeer(customer: Customer): Beer =
    if (customer.age < 18)
      throw UnderAgeException(s"Customer must be older than 18 but was ${customer.age}")
  else
      new Beer

  val youngCustomer = Customer(17)
  try {
    buyBeer(youngCustomer)
  } catch {
    case UnderAgeException(msg) => msg
  }

  // handling errors, functional way
  def parseURL(url: String): Try[URL] = Try(new URL(url))

  //  map
  val protocol = parseURL("https://devsprint.net").map(_.getProtocol)
  val protocolError = parseURL("garbage").map(_.getProtocol)

  def inputStreamFromUrl(url: String) = parseURL(url).map { u =>
    Try(u.openConnection()).map( conn => Try(conn.getInputStream))
  }

  // flat map
  def inputStreamForUrl(url: String) = parseURL(url).flatMap { u =>
    Try(u.openConnection()).flatMap( conn => Try(conn.getInputStream))
  }

  // for comprehension

  def getURLContent(url: String): Try[Iterator[String]] =
    for {
      url <- parseURL(url)
      connection <- Try(url.openConnection)
      is <- Try(connection.getInputStream)
      source = Source.fromInputStream(is)
    } yield source.getLines()

  // pattern matching

  val result = getURLContent("https://wwww.amamzon.com") match {
    case scala.util.Success(lines) => lines.foreach(println)
    case scala.util.Failure(exception) => println(s"Problem rendering url content: ${exception.getMessage}")
  }

  // recovery from failure
  val content = getURLContent("https://www.amazon.com") recover {
    case e: FileNotFoundException => Iterator("Requested page does not exist.")
    case e: MalformedURLException => Iterator("Make sure you enter a valid URL.")
    case _ => Iterator("An unexpected error has occurred.")
  }
}
