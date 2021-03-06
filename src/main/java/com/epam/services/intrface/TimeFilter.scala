package com.epam.services.intrface

import org.apache.spark.sql.{Dataset, Row}

trait TimeFilter {
  def timeStampFilter(startTime: String, endTime: String, events: Dataset[Row]): Dataset[Row]
}
