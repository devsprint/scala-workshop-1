package io.devsprint.error.handling.option

object exercise2 {

  trait OptionCombinator {

    def traverse[A,B](a: List[A])(f: A => Option[B]): Option[List[B]]

  }

  object TraverseCombinator extends OptionCombinator {

    override def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
      a match {
        case Nil => Some(Nil)
        case x :: xs => map2(f(x), traverse(xs)(f))(_ :: _)
      }


    private def map2[A, B, C](a: Option[A], b: Option[B])(f: (A,B) => C): Option[C] =
      a flatMap (aa => b map(bb => f(aa, bb)))
  }

}
