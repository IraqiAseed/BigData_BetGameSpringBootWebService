package com.epam.services.implementation.manager

import com.epam.model.BasicStatistics
import com.epam.repo.EventRepository
import com.epam.services.implementation.statistics.GameStatistics
import com.epam.services.intrface.{TimeFilter, Validator}
import org.apache.spark.sql
import org.apache.spark.sql.Encoders
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


  def showGameStatistics(startTime: String, endTime: String, game: String): java.util.List[java.util.List[BasicStatistics]] = {

    var statisticsList: java.util.List[java.util.List[BasicStatistics]] = new java.util.ArrayList[java.util.List[BasicStatistics]]

    var events = eventRepository.readEvents()

    val initValidators = initValidatorList.asScala

    events = timeFilter.timeStampFilter(startTime = startTime, endTime = endTime, events = events)
    events.persist(StorageLevel.MEMORY_AND_DISK)

    for (i <- initValidators.indices) {
      events = initValidators(i).validate(events)
      events.persist(StorageLevel.MEMORY_AND_DISK)
    }

    if (game == null || game.isEmpty) {
      statisticsList = gameStatistics.statistics(events = events)
    }
    else {
      statisticsList = gameStatistics.statistics(game = game, events = events)
    }

    statisticsList
  }
}
