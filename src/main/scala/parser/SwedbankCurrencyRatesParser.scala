package parser

import db.Currency

import scala.collection.mutable

trait SwedbankCurrencyRatesParser {

  def parse(name: String): mutable.Seq[Currency];

}
