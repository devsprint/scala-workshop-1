package io.devsprint.fp.intro.pattern.matching


object translator2 {


  final case class ExternSortColumn(columnName: String)
  final case class ElasticsearchMapping(mappings: List[String])

  object mappings {
    private val internalMapping = Map(
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

    private def toElasticsearchMapping(externSortColumn: ExternSortColumn): ElasticsearchMapping = {
     internalMapping.get(externSortColumn.columnName).map(ElasticsearchMapping).getOrElse(ElasticsearchMapping(List(externSortColumn.columnName)))
    }

    implicit def toElasticsearchMappings(externSortColumns: List[ExternSortColumn]): List[ElasticsearchMapping] =
      externSortColumns.map(toElasticsearchMapping)

  }

  object test {
    def main(args: Array[String]): Unit = {
      import mappings._
      val input = List(ExternSortColumn("customer"), ExternSortColumn("title"), ExternSortColumn("unknown"))
      val output: List[ElasticsearchMapping] = input
      output.foreach(println)
    }
  }



}
