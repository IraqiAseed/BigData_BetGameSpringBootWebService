package com.epam.repo

import com.epam.model.Event
import org.apache.spark
import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource(Array("classpath:application.properties"))
class EventRepository(sparkSession: SparkSession) {

  def readEvents(): Dataset[Row] = {

    val schema = spark.sql.Encoders.product[Event].schema
    sparkSession.read.option("multiline", "true").schema(schema).json("data/generated_event.json")

  }


}


