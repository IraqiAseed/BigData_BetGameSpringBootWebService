package com.epam.services

import com.epam.repo.EventRepository
import org.apache.spark.sql.{Dataset, Row}
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

  def validateAll(startTime: String, endTime: String): Dataset[Row] = {

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

    enrichmentator.showUsersDataFromIdDf(events)

    events
  }
}
