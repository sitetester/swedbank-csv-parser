package parser

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import db.Currency
import org.scalatest.FunSuite

class SwedbankCurrencyRatesCsvParserTest extends FunSuite {

  test("SwedbankCurrencyRatesCsvParser.parse") {
    val parsedCurrencies = SwedbankCurrencyRatesCsvParser.parse("20200111cr.csv")
    assert(parsedCurrencies.size == 29)

    val date = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm").format(LocalDateTime.now)

    assert(
      parsedCurrencies.head == Currency("AED", "0.00000", "0.00000", "4.30500", "3.86750", date))
    assert(
      parsedCurrencies.last == Currency("ZAR", "0.00000", "0.00000", "16.62600", "14.93700", date))
  }
}
