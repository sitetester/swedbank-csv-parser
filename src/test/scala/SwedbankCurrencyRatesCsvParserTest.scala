import org.scalatest.FunSuite

class SwedbankCurrencyRatesCsvParserTest extends FunSuite {

  test("SwedbankCurrencyRatesCsvParser.parse") {

    assert(SwedbankCurrencyRatesCsvParser.parse("20200111cr.csv") === true)

  }
}


