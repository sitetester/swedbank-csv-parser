import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import slick.jdbc.SQLiteProfile.api._
import slick.lifted.TableQuery

import scala.collection.mutable
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.io.Source

object SwedbankCurrencyRatesCsvParser {

  def parse(name: String): Boolean = {
    var currencyList = mutable.Seq[Currency]()
    val date = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm").format(LocalDateTime.now)

    for ((l, _) <- Source.fromResource(name).getLines.zipWithIndex.drop(2)) {

      val a = l.split(",").map(a => a.replaceAll("\"", ""))
      currencyList = currencyList :+ Currency(a(0), a(1), a(2), a(3), a(4), date)
    }

    importCurrencies(currencyList)
  }

  def importCurrencies(currencyList: mutable.Seq[Currency]): Boolean = {
    val db = Database.forConfig("dbConfig")
    val currencyTable = TableQuery[CurrencyTable]

    DbSchema.run()

    val insertCurrenciesAction: Future[Option[Int]] = db.run(currencyTable ++= currencyList)
    Await.result(insertCurrenciesAction, 2.seconds)

    true
  }
}
