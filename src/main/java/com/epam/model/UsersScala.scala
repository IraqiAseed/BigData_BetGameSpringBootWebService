package com.epam.model

import scala.collection.JavaConverters._
import scala.language.implicitConversions


case class UsersScala(id:Integer,name:String,lastName:String,countryOfOrigin:String,email:String)

object UsersAdapter {
  implicit def toProduct(users: Users): UsersScala = {
    UsersScala(users.getId, users.getName, users.getLastName, users.getCountryOfOrigin, users.getEmail)
  }

  implicit def toProduct1(users: java.util.List[Users]): List[UsersScala] = {

    users.asScala.toList.map(users => UsersScala(users.getId, users.getName, users.getLastName, users.getCountryOfOrigin, users.getEmail))
  }
}
