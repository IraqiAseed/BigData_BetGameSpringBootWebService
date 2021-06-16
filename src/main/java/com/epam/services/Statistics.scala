package com.epam.services

import com.epam.model.BasicStatistics
import org.apache.spark.sql.{Dataset, Row}

trait Statistics {

  var listOfStatisticsTypes: List[String] = List("bet", "win", "profit")

  def statistics(events: Dataset[Row], game: String): Unit

  def statistics(events: Dataset[Row]): Unit
}
