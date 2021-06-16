package com.epam.services

import com.epam.repo.EventRepository
import org.apache.spark.sql.{Dataset, Row}
import org.apache.spark.storage.StorageLevel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util
import scala.collection.JavaConverters.asScalaBufferConverter

@Component
class StatisticsManager(eventRepository: EventRepository, timeFilter: TimeFilter) {

  @Autowired
  var initValidatorList: util.List[Validator] = _

  @Autowired
  var gameStatistics: GameStatistics = _

  def showGameStatistics(startTime: String, endTime: String, game: String): Unit = {

    var events = eventRepository.readEvents()

    val initValidators = initValidatorList.asScala

    events = timeFilter.timeStampFilter(startTime = startTime, endTime = endTime, events = events)
    events.persist(StorageLevel.MEMORY_AND_DISK)

    for (i <- initValidators.indices) {
      events = initValidators(i).validate(events)
      events.persist(StorageLevel.MEMORY_AND_DISK)
    }

    if (game == null || game.isEmpty) {
      gameStatistics.statistics(events = events)
    }
    else {
      gameStatistics.statistics(game = game, events = events)
    }

  }
}
