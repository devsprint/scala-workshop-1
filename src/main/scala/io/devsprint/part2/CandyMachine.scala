package io.devsprint.part2

import io.devsprint.part2.StatePattern.State

/**
  * Finite state machine automation that models a simple candy dispenser.
  * There are 2 types of inputs:
  * - insert a coin
  * - turn the knob to dispense a candy.
  *
  * The machine could be in one of two states:
  * - locked
  * - unlocked
  *
  * It tracks also how many candies are left  and how many conins it contains.
  *
  * Rules:
  *
  * - inserting a coin into a locked machine it cause it to unlock, if there's any candy left
  * - turning the knob on unlocked machine will cause it to dispense candy and become locked.
  * - turning the knob in a locked machine or inserting a coin into an unlocked machine does nothing.
  * - a machine that is out of candy ignores all inputs.
  *
  */
object CandyMachine extends App {
  import State._

  sealed trait Input
  case object Coin extends Input
  case object Turn extends Input

  case class Machine(locked: Boolean, candies: Int, coins: Int)

  def update = (i: Input) => (s: Machine) =>
    (i, s) match {
      case (_, Machine(_, 0, _)) => s
      case (Coin, Machine(false, _, _)) => s
      case (Turn, Machine(true, _, _)) => s
      case (Coin, Machine(true, candy, coins)) =>
        Machine(false, candy, coins +1)
      case (Turn, Machine(false, candy, coins)) =>
        Machine(true, candy -1, coins)
     }

  def simulate(inputs: List[Input]): State[Machine, (Int, Int)] = for {
    _ <- sequence(inputs map (modify[Machine] _ compose update))
    s <- get
  } yield (s.coins, s.candies)

  val default = Machine(true, 5, 10)
  val result = simulate(List(Coin, Turn, Coin, Turn, Coin, Turn, Coin, Turn)) run default

  println(result._1)  // it should be (14, 1)


}
