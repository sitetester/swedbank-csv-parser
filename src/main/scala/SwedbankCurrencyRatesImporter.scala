import db.CurrencyTable
import parser.{SwedbankCurrencyRatesCsvParser, SwedbankCurrencyRatesParser}
import slick.jdbc.SQLiteProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object SwedbankCurrencyRatesImporter {

  val db = Database.forConfig("dbConfig")
  val parser: SwedbankCurrencyRatesParser = SwedbankCurrencyRatesCsvParser

  def importFile(name: String): Boolean = {

    val currencyList = parser.parse(name)
    val currencyTable = TableQuery[CurrencyTable]

    val insertCurrenciesAction: Future[Option[Int]] = db.run(currencyTable ++= currencyList)
    Await.result(insertCurrenciesAction, 2.seconds)

    true
  }
}
