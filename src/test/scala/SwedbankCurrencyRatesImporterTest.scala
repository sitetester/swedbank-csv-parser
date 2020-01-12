import org.scalatest.{BeforeAndAfter, FunSuite}
import parser.{SwedbankCurrencyRatesCsvParser, SwedbankCurrencyRatesUrlParser}

class SwedbankCurrencyRatesImporterTest extends FunSuite with BeforeAndAfter {

  before {
    db.DbSchema.run()
  }

  test("SwedbankCurrencyRatesImporterTest.importCurrencies-CsvExample") {
    // import from CSV
    assert(
      SwedbankCurrencyRatesImporter
        .importCurrencies("currency_rates.csv", SwedbankCurrencyRatesCsvParser) === true)
  }

  test("SwedbankCurrencyRatesImporterTest.importCurrencies-UrlExample") {
    // import from URL
    assert(
      SwedbankCurrencyRatesImporter
        .importCurrencies(
          "https://www.swedbank.lt/private/d2d/payments/rates/currency?language=ENG",
          SwedbankCurrencyRatesUrlParser) === true)
  }
}
