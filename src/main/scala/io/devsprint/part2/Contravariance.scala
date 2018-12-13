package io.devsprint.part2

import io.devsprint.part2.Covariance.{Animal, Cat}

object Contravariance extends App {

  abstract class Printer[-A] {
    def print(value: A): Unit
  }

  class AnimalPrinter extends Printer[Animal] {
    override def print(animal: Animal): Unit =
      println("The animal's name is: " + animal.name)
  }

  class CatPrinter extends Printer[Cat] {
    override def print(cat: Cat): Unit =
      println("The cat's name is: " + cat.name)
  }


  val myCat: Cat = Cat("boots")

  def printMyCat(printer: Printer[Cat]): Unit = {
    printer.print(myCat)
  }

  val catPrinter: Printer[Cat] = new CatPrinter
  val animalPrinter: Printer[Animal] = new AnimalPrinter

  printMyCat(catPrinter)
  printMyCat(animalPrinter)

}
