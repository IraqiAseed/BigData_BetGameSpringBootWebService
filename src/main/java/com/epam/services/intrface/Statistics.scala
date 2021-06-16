package com.epam.services.intrface

import com.epam.model.BasicStatistics
import org.apache.spark.sql.{Dataset, Row}

trait Statistics {

  var listOfStatisticsTypes: List[String] = List("bet", "win", "profit")

  def statistics(events: Dataset[Row], game: String): java.util.List[java.util.List[BasicStatistics]]

  def statistics(events: Dataset[Row]): java.util.List[java.util.List[BasicStatistics]]
}
