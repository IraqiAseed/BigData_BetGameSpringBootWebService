package com.epam.services.implementation.statistics

import com.epam.model.BasicStatistics
import com.epam.services.intrface.Statistics
import org.apache.spark.sql
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, Encoders, Row, SparkSession, functions}
import org.springframework.stereotype.Component

@Component
class GameStatistics extends Statistics {

  override def statistics(events: Dataset[Row], game: String): java.util.List[java.util.List[BasicStatistics]] = {


    val encoder: sql.Encoder[BasicStatistics] = Encoders.product[BasicStatistics]
    var statisticsList: java.util.List[java.util.List[BasicStatistics]] = new java.util.ArrayList[java.util.List[BasicStatistics]]

    for (statisticsType <- listOfStatisticsTypes) {
      var eventsStatistics = events
        .withColumn("profit", col("win").minus(col("bet")))
        .filter(col("gameName").equalTo(game))
        .agg(
          avg(statisticsType).alias("avg"),
          max(statisticsType).alias("max"),
          min(statisticsType).alias("min"))
        .withColumn("game", lit(game))
        .withColumn("statisticsType", lit(statisticsType))

      eventsStatistics.show(false)
      statisticsList.add(eventsStatistics.as[BasicStatistics](encoder).collectAsList())

    }

    statisticsList

  }

  override def statistics(events: Dataset[Row]): java.util.List[java.util.List[BasicStatistics]] = {
    val encoder: sql.Encoder[BasicStatistics] = Encoders.product[BasicStatistics]
    var statisticsList: java.util.List[java.util.List[BasicStatistics]] = new java.util.ArrayList[java.util.List[BasicStatistics]]


    for (statisticsType <- listOfStatisticsTypes) {
      var eventsStatistics = events
        .withColumn("profit", col("win").minus(col("bet")))
        .groupBy(col("gameName"))
        .agg(
          avg(statisticsType).alias("avg"),
          max(statisticsType).alias("max"),
          min(statisticsType).alias("min")) // min(statisticsType).alias(s"MIN $statisticsType"))
        .withColumn("statisticsType", lit(statisticsType))
        .withColumnRenamed("gameName", "game")

      eventsStatistics.show()

      statisticsList.add( eventsStatistics.as[BasicStatistics](encoder).collectAsList() )
    }
    statisticsList
  }
}
