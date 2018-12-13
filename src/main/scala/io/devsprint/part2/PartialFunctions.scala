package io.devsprint.part2

object PartialFunctions extends App {

  // total function
  val aTotalFunction = (x: Int) => x +1

  val aFunction = (x: Int) =>
    if (x == 2) 33
    else if (x == 3) 44
    else if (x == 4) 55
    else throw new FunctionNoApplicableException

  class FunctionNoApplicableException extends RuntimeException

  val aNicerFunction = (x: Int) => x match {
    case 1 => 22
    case 2 => 33
    case 3 => 44
  }

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 22
    case 2 => 33
    case 3 => 44
  } // partial function value


  println(aPartialFunction.isDefinedAt(5))

  val lifted = aPartialFunction.lift

  println(lifted(2))

  val chain = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }

  println(chain(45))

  // HOFs
  val squareRoot: PartialFunction[Double, Double] = {
    case d: Double if d > 0 => Math.sqrt(d)
  }

  val list: List[Double] = List(4, 16, 25, -9)
  println(list.collect(squareRoot))

  //println(list.map(squareRoot))



}
