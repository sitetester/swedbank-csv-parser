package db

import java.io.File

import slick.jdbc.SQLiteProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._

case class Currency(currency: String,
                    cashBuy: String,
                    cashSell: String,
                    transferBuy: String,
                    transferSell: String,
                    createdAt: String)

class CurrencyTable(tag: Tag) extends Table[Currency](tag, "currencies") {

  def * = (currency, cashBuy, cashSell, transferBuy, transferSell, createdAt).mapTo[Currency]

  def currency: Rep[String] = column[String]("currency")

  def cashBuy: Rep[String] = column[String]("cashBuy")

  def cashSell: Rep[String] = column[String]("cashSell")

  def transferBuy: Rep[String] = column[String]("transferBuy")

  def transferSell: Rep[String] = column[String]("transferSell")

  def createdAt = column[String]("createdAt")
}

object DbSchema {

  def run(): Unit = {
    // delete only in dev environment
    new File("currency_rates.db").delete()

    val db = Database.forConfig("dbConfig")
    val currencies = TableQuery[CurrencyTable]

    println(currencies.schema.create.statements.mkString(", "))
    Await.result(db.run(currencies.schema.create), 2.seconds)
  }
}
