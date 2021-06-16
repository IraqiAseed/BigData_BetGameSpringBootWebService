package com.epam.services

import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, Row, SparkSession, functions}
import org.springframework.stereotype.Component

@Component
class GameStatistics(sparkSession: SparkSession) extends Statistics {

  override def statistics(events: Dataset[Row], game: String): Unit = {

    //no need for encoder, please advise if need it for both functions
    //val encoder: sql.Encoder[BasicStatistics] = Encoders.product[BasicStatistics]
   // var list: java.util.List[java.util.List[BasicStatistics]] = new util.ArrayList[java.util.List[BasicStatistics]]

    for (statisticsType <- listOfStatisticsTypes) {
      var eventsStatistics = events
        .withColumn("profit", col("win").minus(col("bet")))
        .filter(col("gameName").equalTo(game))
        .agg(
          avg(statisticsType).alias(s"AVG $statisticsType"),//"avg"
          functions.max(statisticsType).alias(s"MAX $statisticsType"),//max
          functions.min(statisticsType).alias(s"MIN $statisticsType"))//min
        .withColumn("GAME NAME", lit(game)) //.withColumn("game", lit(game))


      eventsStatistics.show(false)
     // list.add(eventsStatistics.as[BasicStatistics](encoder).collectAsList)
    }


  }

  override def statistics(events: Dataset[Row]): Unit = {

    for (statisticsType <- listOfStatisticsTypes) {
      var eventsStatistics = events
        .withColumn("profit", col("win").minus(col("bet")))
        .groupBy(col("gameName"))
        .agg(
          avg(statisticsType).alias(s"AVG $statisticsType"),
          max(statisticsType).alias(s"MAX $statisticsType"),
          min(statisticsType).alias(s"MIN $statisticsType"))

      eventsStatistics.show()
    }

  }
}
