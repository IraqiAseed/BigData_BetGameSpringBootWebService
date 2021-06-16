package com.epam.services.implementation.suspicious

import com.epam.services.intrface.Suspicious
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{Dataset, Row}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component


@Component
@PropertySource(Array("classpath:user.properties"))
class RatioWinBet(@Value("${betRatio}") val ratio: Double) extends Suspicious {

  override def suspicious(events: Dataset[Row]): Dataset[Row] = {
    events
      .filter(col("win").divide(col("bet")).gt(ratio))
      .distinct()


  }
}