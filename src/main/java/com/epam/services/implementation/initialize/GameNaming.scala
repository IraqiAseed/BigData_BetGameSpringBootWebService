package com.epam.services.implementation.initialize

import com.epam.services.intrface.Validator
import org.apache.spark.sql.functions.{col, not}
import org.apache.spark.sql.{Dataset, Row}
import org.springframework.stereotype.Component

@Component
class GameNaming extends Validator {
  override def validate(events: Dataset[Row]): Dataset[Row] = {
    events
      .filter(not(col("eventCountry").equalTo("USA").and(col("gameName").contains("-demo"))))
  }
}
