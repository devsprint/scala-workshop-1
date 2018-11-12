package io.devsprint.error.handling.option

object exercise1 {


  trait OptionCombinator {

    def sequence[A](a: List[Option[A]]): Option[List[A]]

  }

  object SequenceCombinator extends OptionCombinator {

    override def sequence[A](a: List[Option[A]]): Option[List[A]] =
      a match {
        case Nil => Some(Nil)
        case x :: xs => x match {
          case None => None
          case Some(head) => sequence(xs) match {
            case None => None
            case Some(list) => Some (head :: list)

          }
        }
      }

    def sequence2[A](a: List[Option[A]]): Option[List[A]] =

      a.foldLeft(Option(List.empty[A])) {
        case (Some(sofar), Some(value)) => Some(sofar :+ value)
        case (_, _) => None
      }


  }

}
