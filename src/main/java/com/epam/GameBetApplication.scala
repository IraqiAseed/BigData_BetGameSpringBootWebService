package com.epam

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class GameBet

object GameBetApplication extends App {

  val context: ConfigurableApplicationContext = SpringApplication.run(classOf[GameBet])

}
