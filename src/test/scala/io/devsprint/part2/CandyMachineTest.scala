package io.devsprint.part2

import io.devsprint.part2.CandyMachine._
import org.scalatest.{FlatSpec, Matchers}


class CandyMachineTest extends FlatSpec with Matchers {

  val inputCoin = List(Coin)
  val inputTurn = List(Turn)


  "CandyMachine" should "inserting a coin into a locked machine it cause it to unlock, if there's any candy left" in {

    val machine = Machine(true, 1, 0)
    val result = simulate(inputCoin) run machine

    result._2.locked shouldBe false

  }

  "CandyMachine" should "turning the knob on unlocked machine will cause it to dispense candy and become locked." in {
    val machine = Machine(false, 1, 1)
    val result = simulate(inputTurn) run machine
    result._2.locked shouldBe true
    result._2.candies shouldBe 0
  }

}
