package com.epam.services

import org.apache.spark.sql.functions.{col, when}
import org.apache.spark.sql.{Dataset, Row}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource(Array("classpath:user.properties"))
class CurrencyConvertor(@Value("${currencyRateDollarToEuro}") val currency:Double) extends Validator {

  override def validate(events: Dataset[Row]): Dataset[Row] = {

    events
      .withColumn("bet", when(col("eventCurrencyCode").equalTo("EUR"),
        col("bet").divide(currency)).otherwise(col("bet")))
      .withColumn("win", when(col("eventCurrencyCode").equalTo("EUR"),
        col("win").divide(currency)).otherwise(col("win")))
      .drop("eventCurrencyCode")

  }
}
