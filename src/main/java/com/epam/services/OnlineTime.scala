package com.epam.services

import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{Dataset, Row}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource(Array("classpath:user.properties"))
class OnlineTime(@Value("${onlineHoursTime}") val hours: Int) extends Suspicious {

  override def suspicious(events: Dataset[Row]): Dataset[Row] = {

    val seconds = hours * 60 * 60

    events
      .filter(col("onlineTimeSecs").gt(seconds))
      .distinct()

  }
}
