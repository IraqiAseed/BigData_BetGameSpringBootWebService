package com.epam.services

import org.apache.spark.sql.{Dataset, Row}

trait Validator {
  def validate(events: Dataset[Row]): Dataset[Row]
}
