package com.starcolon.tao.forex.endpoint

import scalaj.http._
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.starcolon.tao.common.parser.Parser._
import com.starcolon.tao.forex.types._
import com.starcolon.tao.cache.CacheRegistry

abstract class RESTEndpoint {
  def apiURL: String 
  def get(params: Map[String, String]): HttpResponse[String] = Http(apiURL).params(params).asString
}

class ForexEndPoint(apiKey: String) extends RESTEndpoint {

  override def apiURL = s"https://forex.1forge.com/1.0.2/convert"
  private def printHTTPError(resp: HttpResponse[String]) = {
    println(s"[HTTPError ${resp.code}]")
    println(resp.body)
  }

  def getExchange(params: Map[String, String]): Option[ServiceOutput] = {
    val resp = get(params + ("api_key" -> apiKey))
    resp.code match {
      case 200 => parseAs[ExchangeRate](resp.body) match {
        case Some(msg) => Some(msg)
        case _ => parseAs[ServiceError](resp.body)
      }
      case _ => Some(ServiceError(s"HTTP Error ${resp.code}", resp.body))
    }
  }
}

class CacheableForexEndPoint(apiKey: String, ttl: Int = 300) extends ForexEndPoint(apiKey) {
  
  val cache = new CacheRegistry(ttl)
  
  override def getExchange(params: Map[String, String]): Option[ServiceOutput] = {

    val pair = Seq(params("from"), params("to")).mkString("-")
    cache.readKey(pair) match {
      case Some(ratioStr) => 
        println(Console.CYAN + s"[RESTORE FROM CACHE] $pair" + Console.RESET)
        Some(mimicResponse(ratioStr.toDouble, params))
      case None => 
        super.getExchange(params) match {
          case None => None
          case Some(res: ExchangeRate) =>
            // Cache the ratio
            val ratio = res.value / params("quantity").toDouble
            println(Console.CYAN + s"[CACHING] $pair = $ratio" + Console.RESET)
            cache.writeKey(pair, ratio.toString)
            Some(res)
          case Some(others) => Some(others)
        }
    }
  }

  private def mimicResponse(ratio: Double, params: Map[String, String]): ServiceOutput = {
    val amt = ratio * params("quantity").toDouble
    val from = params("from")
    val to   = params("to")
    val qty  = params("quantity")
    ExchangeRate(amt, s"$qty $from is worth $amt $to", "")
  }
}
