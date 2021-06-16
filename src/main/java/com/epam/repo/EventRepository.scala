package com.epam.repo

import com.epam.GameBetApplication.context
import com.epam.model.{Event, Users}
import org.apache.spark
import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.{Component, Repository}

@Component
@PropertySource(Array("classpath:application.properties"))
@PropertySource(Array("classpath:user.properties")) //todo userProperties class for this properties is better approach !!
class EventRepository(sparkSession: SparkSession, @Value("${Games}") val games: java.util.List[String]) {

  def readEvents(): Dataset[Row] = {

    val schema = spark.sql.Encoders.product[Event].schema
    sparkSession.read.option("multiline", "true").schema(schema).json("data/generated_event.json")

  }


}


