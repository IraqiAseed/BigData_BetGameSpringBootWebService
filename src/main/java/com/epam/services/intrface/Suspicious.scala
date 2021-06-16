package com.epam.services.intrface

import com.epam.config.UserProperties
import org.apache.spark.sql.{Dataset, Row}

trait Suspicious {
  val suspiciousOnlineTimeHours = UserProperties.getOnlineUserDefinedHoursWhichIsSuspicious

  def suspicious(events: Dataset[Row]): Dataset[Row]
}
