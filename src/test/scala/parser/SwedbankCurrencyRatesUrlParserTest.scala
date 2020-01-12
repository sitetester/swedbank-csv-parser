package parser

import org.scalatest.FunSuite

class SwedbankCurrencyRatesUrlParserTest extends FunSuite {

  test("SwedbankCurrencyRatesHtmlParser.parse") {
    val path = "https://www.swedbank.lt/private/d2d/payments/rates/currency?language=ENG"
    SwedbankCurrencyRatesUrlParser.parse(path)
  }
}
