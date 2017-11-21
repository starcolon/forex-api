package com.starcolon.tao.forex.types

trait ServiceOutput

case class ExchangeRate(value: Double, text: String, timestamp: String) extends ServiceOutput

case class ServiceError(error: String, message: String) extends ServiceOutput