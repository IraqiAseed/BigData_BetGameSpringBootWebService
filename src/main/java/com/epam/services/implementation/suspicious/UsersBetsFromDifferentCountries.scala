package com.epam.services.implementation.suspicious

import com.epam.services.intrface.UsersSuspicious
import org.apache.spark.sql.functions.{col, countDistinct}
import org.apache.spark.sql.{Dataset, Row}
import org.springframework.stereotype.Component

@Component
class UsersBetsFromDifferentCountries extends UsersSuspicious {
  override def suspicious(events: Dataset[Row]): Dataset[Row] = {

    events
      .groupBy("userId")
      .agg(countDistinct("eventCountry").alias("countries count"))
      .filter(col("countries count").gt(1))
      .drop("countries count")

  }
}



