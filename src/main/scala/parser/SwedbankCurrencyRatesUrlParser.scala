package parser

import db.Currency

import scala.collection.JavaConverters._
import scala.collection.mutable

object SwedbankCurrencyRatesUrlParser extends SwedbankCurrencyRatesParser {

  override def parse(path: String): mutable.Seq[Currency] = {

    import org.jsoup.Jsoup
    val doc = Jsoup.connect(path).get

    val currencyDate =
      doc
        .select("ui-buttonbar h2")
        .text()
        .substring(0, 11)
        .split("\\.")
        .map(_.trim)
        .reverse
        .mkString("-")

    val trElements = doc.select("ui-table table tr")
    var currencies = mutable.Seq[Currency]()

    for ((tr, _) <- trElements.asScala.zipWithIndex.drop(1))
      currencies = currencies :+ Currency(
        tr.children.get(0).text().substring(0, 3),
        "0",
        "0",
        tr.children.get(1).text(),
        tr.children.get(2).text(),
        currencyDate
      )

    currencies
  }
}
