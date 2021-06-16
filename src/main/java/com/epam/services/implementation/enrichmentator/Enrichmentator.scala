package com.epam.services.implementation.enrichmentator

import com.epam.model.{Users, UsersScala}
import com.epam.services.implementation.initialize.UserService
import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.springframework.stereotype.Component

@Component
class Enrichmentator(sparkSession: SparkSession, userService: UserService) {

  def showUsersDataFromIdDf(usersIdsDf: Dataset[Row]): Dataset[Row] = {

    val userDF = sparkSession.createDataFrame(userService.getAllUsers, classOf[Users])

    val requestedUsers:Dataset[Row] = usersIdsDf.join(userDF, userDF("id") === (usersIdsDf("userId"))).drop("userId")

    requestedUsers.show(false)

    requestedUsers
  }


}