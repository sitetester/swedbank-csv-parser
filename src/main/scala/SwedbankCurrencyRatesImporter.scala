import db.CurrencyTable
import parser.{SwedbankCurrencyRatesParser, SwedbankCurrencyRatesUrlParser}
import slick.jdbc.SQLiteProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object SwedbankCurrencyRatesImporter {

  val db = Database.forConfig("dbConfig")
  val parser: SwedbankCurrencyRatesParser = SwedbankCurrencyRatesUrlParser

  // This is a GENERIC importer. It could import data from a variety of ways including CSV or URL
  // Just inject corresponding parser (SwedbankCurrencyRatesCsvParser | SwedbankCurrencyRatesUrlParser) and it will do it's job

  // `path` could be CSV file name in src/test/resources OR URL - https://www.swedbank.lt/private/d2d/payments/rates/currency?language=ENG
  def importCurrencies(path: String, parser: SwedbankCurrencyRatesParser): Boolean = {

    val currencyList = parser.parse(path)
    val currencyTable = TableQuery[CurrencyTable]

    val insertCurrenciesAction: Future[Option[Int]] =
      db.run(currencyTable ++= currencyList)

    Await.result(insertCurrenciesAction, 2.seconds)

    true
  }
}
