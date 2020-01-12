package parser

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import db.Currency

import scala.collection.mutable
import scala.io.Source

object SwedbankCurrencyRatesCsvParser extends SwedbankCurrencyRatesParser {

  def parse(path: String): mutable.Seq[Currency] = {

    var currencyList = mutable.Seq[Currency]()
    val date = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm").format(LocalDateTime.now)

    for ((l, _) <- Source.fromResource(path).getLines.zipWithIndex.drop(2)) {

      val a = l.split(",").map(a => a.replaceAll("\"", ""))
      currencyList = currencyList :+ Currency(a(0), a(1), a(2), a(3), a(4), date)
    }

    currencyList
  }
}
