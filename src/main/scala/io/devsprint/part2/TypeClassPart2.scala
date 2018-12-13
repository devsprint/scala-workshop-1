package io.devsprint.part2

import io.devsprint.part2.TypeClassPart1.JSONSerializer

object TypeClassPart2 extends App {

  object JSONSerializer {
    def serialize[T](value: T)(implicit serializer: JSONSerializer[T]): String =
      serializer.serialize(value)

    def apply[T](implicit serializer: JSONSerializer[T]) = serializer
  }

  implicit object IntSerializer extends JSONSerializer[Int] {
    override def serialize(value: Int): String =
      s"""
         |{
         | "number": $value
         |}
       """.stripMargin
  }

  //println(JSONSerializer.serialize(42))

  //println(JSONSerializer[Int].serialize(42))

}
