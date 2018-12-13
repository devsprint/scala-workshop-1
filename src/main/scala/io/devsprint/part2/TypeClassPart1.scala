package io.devsprint.part2
import java.util.Date

object TypeClassPart1 extends App {

  trait JSONWritable {
    def toJson: String
  }

  case class User(name: String, email: String, age: Int) extends JSONWritable {
    override def toJson: String =
      s"""
         |{
         | "name":"$name",
         | "email":"$email",
         | "age":$age
         |}
       """.stripMargin
  }


  User("John","john@test.com", 30).toJson

  /*
     1. - putem implementa serializarea la json numai pentru tipurile de date pe care noi le construim
     2. - o singura implementare
   */










  // option2 - pattern matching

//  object JSONSerializer {
//    def serialize(value: Any): String = value match {
//      case User(n,e, a) =>
//      case java.util.Date =>
//      case _ =>
//     }
//  }

  /*
    1 - pierdem type safety
    2 - modificam codul de fiecare data cand trebuie sa adaugam support pt un nou tip
    3 - avem o singura implementare.
   */




  trait JSONSerializer[T] {
    def serialize(value: T): String
  }

  object UserSerializer extends JSONSerializer[User] {
    override def serialize(user: User): String =
      s"""
         |{
         | "name":"${user.name}",
         | "email":"${user.email}",
         | "age":${user.age}
         |}
       """.stripMargin
  }

  val john = User("John", "test@test.com", 30)
  println(UserSerializer.serialize(john))

  // 1. putem defini serializer si pentru alte tipuri pe care nu noi le definim
  object DateSerializer extends JSONSerializer[java.util.Date] {
    override def serialize(date: Date): String =
      s"""
         |{
         |  "date":"${date.toString}"
         |}
       """.stripMargin
  }

  // putem defini mai multi serializatori pt acelasi ip de date
  object PartialUserSerializer extends JSONSerializer[User] {
    override def serialize(user: User): String =
      s"""
         |{
         | "name":"${user.name}",
         |}
       """.stripMargin
  }

}

