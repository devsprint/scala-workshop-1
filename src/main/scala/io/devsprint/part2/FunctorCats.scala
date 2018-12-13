package io.devsprint.part2


object FunctorCats extends App {

  import cats.Functor
  import cats.implicits._

  val maybeName = Option("Joe")
  println(Functor[Option].map(maybeName)(_.length))


  def greet(name: String): String = s"Hello $name!"
  println(Functor[Option].map(maybeName)(greet))

  println(Functor[Option].lift(greet)(maybeName))

  val users = List("Joe", "Kate")
  println(Functor[List].fproduct(users)(greet).toMap)

  val optUsers = List(Some("Kate"), None, Some("Joe"))
  val listOptionFunctor = Functor[List] compose Functor[Option]
  println(listOptionFunctor.map(optUsers)(greet))



}
