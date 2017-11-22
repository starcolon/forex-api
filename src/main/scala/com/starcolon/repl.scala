package com.starcolon.tao.repl

import com.starcolon.tao.forex.endpoint.{ForexEndPoint, CacheableForexEndPoint}
import com.starcolon.tao.forex.types._
import com.starcolon.tao.rest.Server
import scala.io.StdIn

object CoreREPL extends App with Server {

  println(Console.CYAN)
  println("**************************")
  println
  println("    Forex API Endpoint    ")
  println
  println("**************************")
  println(Console.RESET)
    
  //repl()
  listen()

  def listen(){
    StdIn.readLine()
    system.terminate()
  }

  def repl(){
    val srcCurrency = readLine("  Which currency do you have? :")
    val srcAmount   = readLine("  How much do you have? : ")
    println(Console.GREEN)
    val dstCurrency = readLine("  Which currency do you want? :")
    println(Console.RESET)

    val res = forex.getExchange(
      Map("from"     -> srcCurrency,
          "to"       -> dstCurrency,
          "quantity" -> srcAmount))

    println(res)
    println
    repl()
  }
}