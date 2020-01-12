package parser

import db.Currency

import scala.collection.mutable

trait SwedbankCurrencyRatesParser {

  def parse(path: String): mutable.Seq[Currency]

}
