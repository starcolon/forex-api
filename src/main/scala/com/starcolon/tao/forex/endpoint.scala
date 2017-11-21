package com.starcolon.tao.forex.endpoint

import scalaj.http._
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.starcolon.tao.common.parser.Parser._

abstract class RESTEndpoint {
  def apiURL: String 
  def get(params: Map[String, String]): HttpResponse[String] = Http(apiURL).params(params).asString
}

class ForexEndPoint extends RESTEndpoint {
  override def apiURL = s"https://forex.1forge.com/1.0.2/convert"

  private def printHTTPError(resp: HttpResponse[String]) = {
    println(s"[HTTPError ${resp.code}]")
    println(resp.body)
  }

  def getAs[T](params: Map[String, String]): Option[T] = {
    val resp = get(params)
    resp.code match {
      case 200 => parseAs[T](resp.body)
      case _ => 
        printHTTPError(resp)
        None
    }
  }
}
