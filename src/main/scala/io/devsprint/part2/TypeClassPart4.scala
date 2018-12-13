package io.devsprint.part2

import java.util.UUID

object TypeClassPart4 extends App {


  sealed trait Gender

  final case object Male extends Gender

  final case object Female extends Gender

  final case class City(name: String) extends AnyVal

  final case class Country(name: String) extends AnyVal

  final case class ZipCode(name: String) extends AnyVal

  final case class Address(streetName: String,
                           number: String,
                           other: String,
                           city: City,
                           zipCode: ZipCode,
                           country: Country)

  final case class PhoneNumber(number: String) extends AnyVal

  final case class Developer(
                              firstName: String,
                              lastName: String,
                              birthYear: Int,
                              gender: Gender,
                              address: Address,
                              phone: PhoneNumber,
                              skills: List[String])


  /*
    1 - intermediate data types ( primitive data types)
    2 - create a type class for conversion  to intermediate data types
    3 - serialize intermediate date type to json
   */

  sealed trait JSONValue { // intermediate data type
    def stringify: String
  }

  final case class JSONObject(values: Map[String, JSONValue]) extends JSONValue {
    override def stringify: String = values.map {
      case (key, value) => "\"" + key + "\":" + value.stringify
    }.mkString("{", ",", "}")
  }

  final case class JSONString(value: String) extends JSONValue {
    override def stringify: String = "\"" + value + "\""
  }

  final case class JSONNUmber(value: Int) extends JSONValue {
    override def stringify: String = value.toString
  }


  final case class JSONArray(values: List[JSONValue]) extends JSONValue {
    override def stringify: String = values.map(_.stringify).mkString("[", ",", "]")
  }


  val data = JSONObject(Map(
    "developer" -> JSONString("Gabriel"),
    "skills" -> JSONArray(List(
      JSONString("scala"),
      JSONString("sbt"),
      JSONNUmber(423)
    ))
  ))

  println(data.stringify)

  // type class

  /*
    1. type class
    2. type class instances
    3. pimp library
   */

  trait JSONConverter[T] {
    def convert(value: T): JSONValue
  }

  implicit object StringConverter extends JSONConverter[String] {
    override def convert(value: String): JSONValue = JSONString(value)
  }

  implicit object NumberConverter extends JSONConverter[Int] {
    override def convert(value: Int): JSONValue = JSONNUmber(value)
  }

  implicit class JSONOps[T](value: T) {
    def toJSON(implicit converter: JSONConverter[T]): JSONValue =
      converter.convert(value)
  }


  // custom data type:
  implicit object GenderConverter extends JSONConverter[Gender] {
    override def convert(value: Gender): JSONValue = value match {
      case Male => JSONString("male")
      case Female => JSONString("female")
    }
  }

  implicit object CityConverter extends JSONConverter[City] {
    override def convert(value: City): JSONValue = JSONString(value.name)
  }

  implicit object CountryConverter extends JSONConverter[Country] {
    override def convert(value: Country): JSONValue = JSONString(value.name)
  }

  implicit object ZipCodeConverter extends JSONConverter[ZipCode] {
    override def convert(value: ZipCode): JSONValue = JSONString(value.name)
  }

  implicit object PhoneNumberConverter extends JSONConverter[PhoneNumber] {
    override def convert(value: PhoneNumber): JSONValue = JSONString(value.number)
  }

  implicit object AddressConverter extends JSONConverter[Address] {
    override def convert(value: Address): JSONValue = JSONObject(Map(
      "streetName" -> JSONString(value.streetName),
      "number" -> JSONString(value.number),
      "other" -> JSONString(value.other),
      "city" -> CityConverter.convert(value.city), // TODO
      "country" -> CountryConverter.convert(value.country),
      "zipCode" -> ZipCodeConverter.convert(value.zipCode)
    ))
  }

  implicit object DeveloperConverter extends JSONConverter[Developer] {
    override def convert(value: Developer): JSONValue = JSONObject(Map(
      "firstName" -> JSONString(value.firstName),
      "lastName" -> JSONString(value.firstName),
      "birthYear" -> JSONNUmber(value.birthYear),
      "gender" -> GenderConverter.convert(value.gender),
      "address" -> AddressConverter.convert(value.address),
      "phone" -> PhoneNumberConverter.convert(value.phone),
      "skills" -> JSONArray(value.skills.map(skill => JSONString(skill)))
    ))
  }


  val john = Developer(
    "John",
    "Test",
     1971,
     Male,
     Address("Str. Albac", "51A", "", City("Cluj-Napoca"), ZipCode("410086"), Country("Romania")),
     PhoneNumber("+40745596352"),
      List("java", "scala", "docker", "oop")
  )
  println(john.toJSON.stringify)

}
