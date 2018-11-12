package io.devsprint.error.handling.theeither

import java.net.URL

import scala.io.Source

object eithers {

  def getContent(url: URL):Either[String, Source] =
    if (url.getHost.contains("yahoo"))
      Left("Request is blocked")
    else
      Right(Source.fromURL(url))


  // map
  val content: Either[String, Iterator[String]] = getContent(new URL("https://www.google.com")).map(_.getLines)

  // flatMap

  val part1 = new URL("https://www.google.com/part1.html")
  val part2 = new URL("https://www.google.com/part2.html")
  val size: Either[String, Either[String, Int]] = getContent(part1).map { part1Content =>
    getContent(part2).map{ part2Content =>
      part1Content.getLines().size + part2Content.getLines().size
    }

  }

  val size2: Either[String, Int] = getContent(part1).flatMap { part1Source =>
    getContent(part2).map { part2Source =>
      part1Source.getLines().size + part2Source.getLines().size
    }

  }


  // for comprehension

  def averageLineCount(url: URL, url2: URL): Either[String, Int] =
    for {
      source1 <- getContent(url)
      source2 <- getContent(url2)
    } yield (source1.getLines().size + source2.getLines().size) / 2


  // folding

  val maybeEmptyContent = getContent(new URL("https://www.amazon.com")).fold(Iterator(_), _.getLines())

  // handle error

  def Try[A](a: => A): Either[Exception, A] =
    try Right(a)
    catch { case e: Exception => Left(e)}

}
