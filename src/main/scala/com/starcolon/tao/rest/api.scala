package com.starcolon.tao.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.stream.ActorMaterializer
import com.starcolon.tao.forex.endpoint._
import com.starcolon.tao.forex.types._
import spray.json._

trait JSONParser extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val xgFormat = jsonFormat3(ExchangeRate)
}

trait API extends JSONParser {
  
  val apiKey = sys.env("FOREX_API_KEY")
  lazy val forex = new CacheableForexEndPoint(apiKey, ttl=300)
  
  def getAPI: Route = pathPrefix ("forex") {
    path("exchange") {
      get {
        parameters('from.as[String], 'to.as[String], 'quantity.as[String]) {
          (from, to, quantity) => 
            val params = Map("from"     -> from.toUpperCase,
                             "to"       -> to.toUpperCase,
                             "quantity" -> quantity)
            forex.getExchange(params) match {
              case Some(xg: ExchangeRate) => complete(xg)
              case Some(err: ServiceError) => 
                println(err)
                complete(StatusCodes.InternalServerError)
              case n => complete(StatusCodes.InternalServerError)
            }
        }
      }
    }
  }
}