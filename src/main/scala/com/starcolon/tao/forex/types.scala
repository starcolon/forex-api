package com.starcolon.tao.forex.types

trait ServiceOutput

final case class ExchangeRate(value: Double, text: String, timestamp: String) extends ServiceOutput

final case class ServiceError(error: String, message: String) extends ServiceOutput