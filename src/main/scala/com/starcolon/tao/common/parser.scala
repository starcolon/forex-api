package com.starcolon.tao.common.parser

import org.json4s._
import org.json4s.native.JsonMethods._
import scala.util.Try

object Parser {

  implicit val formats = DefaultFormats

  def parseAs[T: Manifest](str: String): Option[T] = Try{ Some(parse(str).extract[T]) }.getOrElse(None)
}