package com.epam.services.implementation.initialize

import com.epam.services.intrface.TimeFilter
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{Dataset, Row}
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
