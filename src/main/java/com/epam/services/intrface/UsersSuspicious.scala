package com.epam.services.intrface

import org.apache.spark.sql.{Dataset, Row}

trait UsersSuspicious {
  def suspicious(events: Dataset[Row]): Dataset[Row]
}
