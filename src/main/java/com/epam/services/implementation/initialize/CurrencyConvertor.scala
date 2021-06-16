package com.epam.services.implementation.initialize

import com.epam.services.intrface.Validator
import org.apache.spark.sql.functions.{col, when}
import org.apache.spark.sql.{Dataset, Row}
import org.springframework.stereotype.Component

@Component
class CurrencyConvertor extends Validator {

  override def validate(events: Dataset[Row]): Dataset[Row] = {

    events
      .withColumn("bet", when(col("eventCurrencyCode").equalTo("EUR"),
        col("bet").divide(currencyDollarToEuro)).otherwise(col("bet")))
      .withColumn("win", when(col("eventCurrencyCode").equalTo("EUR"),
        col("win").divide(currencyDollarToEuro)).otherwise(col("win")))
      .drop("eventCurrencyCode")

  }
}
