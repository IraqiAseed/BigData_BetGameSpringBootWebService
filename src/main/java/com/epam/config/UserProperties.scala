package com.epam.config

object UserProperties {
  private val currencyRateDollarToEuro = 1.1
  private val onlineHoursTime = 5

  def getCurrencyRateUserDefinedFromDollarToEuro:Double = currencyRateDollarToEuro
  def getOnlineUserDefinedHoursWhichIsSuspicious:Int = onlineHoursTime

}
