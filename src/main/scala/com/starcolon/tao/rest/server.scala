package com.starcolon.tao.rest

import scala.concurrent.duration._
import akka.actor._
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import akka.http.scaladsl.server.Route
import com.typesafe.config.ConfigFactory
import scala.concurrent.ExecutionContext

trait Server extends API {
  val config = ConfigFactory.load()
  val addr = "localhost"
  val port = 2017

  implicit val system = ActorSystem("REST")
  implicit val materialiser = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val routes = getAPI

  Http().bindAndHandle(handler = routes, interface = addr, port = port) map { 
    _ => s"SERVER STARTED :$port"} recover { 
      case ex => s"SERVER FAILED TO START : ${ex.getMessage}" }
}