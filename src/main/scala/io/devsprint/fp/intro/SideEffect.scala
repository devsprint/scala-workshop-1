package io.devsprint.fp.intro

object SideEffect {
 /* trait CreditCard {
    def charge(amount: Long): Unit
  }
  case class  Book(name: String, price: Long = 100)


  class Library {

    def buyBook(cc: CreditCard, name: String): Book = {
      val book = Book(name)
      cc.charge(book.price)
      book
    }

  }

  trait Payment {
    def charge(cc: CreditCard, amount: Long): Unit
  }
  class Library2 {

    def buyBook(cc: CreditCard, name: String, p: Payment): Book = {
      val book = Book(name)
      p.charge(cc, book.price)
      book
    }

  }

  class Library3 {
    case class Charge(cc: CreditCard, amount: Long)

    def buyBook(cc: CreditCard, name: String):(Book, Charge) = {
      val book = Book(name)
      (book, Charge(cc, book.price))

    }
  }

  class Library4 {
    case class Charge(cc: CreditCard, amount: Long) {

      def buyBook(cc: CreditCard, name: String):(Book, Charge) = {
        val book = Book(name)
        (book, Charge(cc, book.price))

      }

      def combine(other: Charge): Charge = {
        if (cc == other.cc)
          Charge(cc, amount + other.amount)
        else
          throw new Exception("Can't combine multiple cards.")
      }
    }



    def buyBooks(cc: CreditCard, names: List[String]): (List[Book], Charge) = {
      val purchases: List[(Book, Charge)] = names.map(name => buyBook(cc, name))

      val (books, charges) = purchases.unzip
      (books, charges.reduce( (c1, c2) => c1.combine(c2)))
    }
  }

*/
}
