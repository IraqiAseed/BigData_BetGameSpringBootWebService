package com.epam.services.implementation.manager

import com.epam.model.{BasicStatistics, Users, UsersScala}
import com.epam.repo.EventRepository
import com.epam.services.implementation.enrichmentator.Enrichmentator
import com.epam.services.intrface.{Suspicious, TimeFilter, UsersSuspicious, Validator}
import org.apache.spark.sql
import org.apache.spark.sql.{Dataset, Encoders, Row}
import org.apache.spark.storage.StorageLevel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util
import scala.collection.JavaConverters.asScalaBufferConverter

@Component
class SuspiciousManager(eventRepository: EventRepository, enrichmentator: Enrichmentator, timeFilter: TimeFilter, usersSuspicious: UsersSuspicious) {

  @Autowired
  var suspiciousValidatorsList: util.List[Suspicious] = _

  @Autowired
  var initValidatorList: util.List[Validator] = _

  def validateAll(startTime: String, endTime: String): java.util.List[java.util.List[UsersScala]]  = {

    val encoder: sql.Encoder[UsersScala] = Encoders.product[UsersScala]
    var usersList: java.util.List[java.util.List[UsersScala]] = new java.util.ArrayList[java.util.List[UsersScala]]


    var events = eventRepository.readEvents()

    val initValidators = initValidatorList.asScala
    val suspiciousValidators = suspiciousValidatorsList.asScala

    for (i <- initValidators.indices) {
      events = initValidators(i).validate(events)
      events.persist(StorageLevel.MEMORY_AND_DISK)
    }

    events = timeFilter.timeStampFilter(startTime = startTime, endTime = endTime, events = events)
    events.persist(StorageLevel.MEMORY_AND_DISK)

    for (i <- suspiciousValidators.indices) {
      events = suspiciousValidators(i).suspicious(events)
      events.persist(StorageLevel.MEMORY_AND_DISK)
    }

    events = usersSuspicious.suspicious(events) //this validator changes the DF structure with groupby, so last

    val usersResult = enrichmentator.showUsersDataFromIdDf(events)

    usersList.add(usersResult.as[UsersScala](encoder).collectAsList())

    usersList
  }
}
