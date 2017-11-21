package com.starcolon.tao.repl

import com.starcolon.tao.forex.endpoint.{ForexEndPoint, CacheableForexEndPoint}
import com.starcolon.tao.forex.types._

object CoreREPL extends App {

  val apiKey = sys.env("FOREX_API_KEY")
  val forexAPI = new CacheableForexEndPoint(apiKey, 300)

  println(Console.CYAN)
  println("**************************")
  println
  println("    Forex API Endpoint    ")
  println
  println("**************************")
  println(Console.RESET)
    
  repl()

  def repl(){
    val srcCurrency = readLine("  Which currency do you have? :")
    val srcAmount   = readLine("  How much do you have? : ")
    println(Console.GREEN)
    val dstCurrency = readLine("  Which currency do you want? :")
    println(Console.RESET)

    val res = forexAPI.getExchange(
      Map("from"     -> srcCurrency,
          "to"       -> dstCurrency,
          "quantity" -> srcAmount))

    println(res)
    println
    repl()
  }
}