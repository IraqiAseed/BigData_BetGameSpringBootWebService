package com.epam.services

import com.epam.model.Users
import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.springframework.stereotype.Component

@Component
class Enrichmentator(sparkSession: SparkSession, userService: UserService) {

  def showUsersDataFromIdDf(usersIdsDf: Dataset[Row]): Unit = {

    val userDF = sparkSession.createDataFrame(userService.getAllUsers, classOf[Users])
    usersIdsDf.join(userDF, userDF("id") === (usersIdsDf("userId"))).drop("userId").show(false)

  }


}