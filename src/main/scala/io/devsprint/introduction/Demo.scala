package io.devsprint.introduction

class Demo {

  def main(args: Array[String]): Unit = {
    val list = List("1", "2", "3")

    val ints  = list.map(s => s.toInt)
    //val ints2 = list.map(_.toInt)

   val orders = List.empty[Order]
  def products = orders.flatMap(o => o.products)

  }

  {

    // object expressions
    val PI = 3.14
    val radius = 2.0

    // function applications
    val area = radius * radius * PI

    // conditional expressions
    if (area > 4.0 )
      "big circle"
    else {
      "small circle"
    }


  }


}

case class Product(id: Int, category: String)
case class Order(id: Int, products: List[Product])
