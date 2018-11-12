package io.devsprint.async

object connection {

    sealed trait Currency
    case object USD extends Currency
    case object CHF extends Currency
    case object EUR extends Currency

    def getCurrentQuote(currency: Currency): Double = currency match {
      case USD => 4.0733
      case CHF => 4.0826
      case EUR => 4.6621
    }

    def buy(amount: Double, quote: Double ): Double = amount* quote

}
