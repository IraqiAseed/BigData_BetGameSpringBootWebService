package com.epam.services.implementation.suspicious

import com.epam.services.intrface.Suspicious
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{Dataset, Row}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
class OnlineTime extends Suspicious {

  override def suspicious(events: Dataset[Row]): Dataset[Row] = {

    val seconds = suspiciousOnlineTimeHours * 60 * 60

    events
      .filter(col("onlineTimeSecs").gt(seconds))
      .distinct()

  }
}
