package io.devsprint.part2


object UpperTypeBounds extends App {

  abstract class Animal {
    def name: String
  }
  abstract class Pet extends Animal

  case class Dog(name: String) extends Pet
  case class Cat(name: String) extends Pet

  trait Printer[ T <: Pet] {
    def print(t: T): Unit = println(t.name)
  }

  object CatPrinter extends Printer[Cat]

  CatPrinter.print(Cat("Tom"))

  object PetPrinter extends Printer[Pet]


}
