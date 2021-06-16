package com.epam.services

import org.apache.spark.sql.functions.{col, not, when}
import org.apache.spark.sql.{Dataset, Row}
import org.apache.spark.storage.StorageLevel
import org.springframework.stereotype.Component
import java.sql.Timestamp

@Component
class Period extends TimeFilter {

  override def timeStampFilter(startTime: String, endTime: String, events: Dataset[Row]): Dataset[Row] = {

    events
      .withColumn("eventTime", col("eventTime").cast("timestamp"))
      .filter(col("eventTime").gt(Timestamp.valueOf(startTime)))
      .filter(col("eventTime").lt(Timestamp.valueOf(endTime)))


  }
}
