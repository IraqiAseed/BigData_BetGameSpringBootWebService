package com.epam.services.intrface

import com.epam.config.UserProperties
import org.apache.spark.sql.{Dataset, Row}

trait Validator {

  val currencyDollarToEuro = UserProperties.getCurrencyRateUserDefinedFromDollarToEuro
  def validate(events: Dataset[Row]): Dataset[Row]
}
