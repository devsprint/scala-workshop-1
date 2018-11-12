package io.devsprint.fp.intro.pattern.matching

object translator {

  private val mappings = Map(
    "date" -> List("isValidFrom"),
    "customer" -> List("contact.name.keyword", "contact.name2.keyword"),
    "vehicle" -> List("vehicle.vehicleType.model.brand.name.keyword",
      "vehicle.vehicleType.name.keyword"),
    "licensePlate" -> List("vehicle.licensePlate.keyword"),
    "stammnummer"->List("vehicle.stammnummer.keyword"),
    "title" -> List("title.keyword"),
    "mechanic" -> List.empty,
    "price" -> List("total"),
    "vin" -> List("vehicle.vin.keyword"),
    "mobilePhone" -> List("contact.phoneMobile.keyword"),
    "status" -> List("kbItemStatusId")
  )


  def getElasticMappingSortValuesForOrders(x: String): List[String] = mappings.getOrElse(x, List(x))


  def main(args: Array[String]): Unit = {
    val input = List("customer", "title", "unknown")
    input.map(getElasticMappingSortValuesForOrders).foreach(println)
  }

}

