package com.epam.services

import org.apache.spark.sql.{Dataset, Row}

trait Suspicious {
  def suspicious(events: Dataset[Row]): Dataset[Row]
}
