name         := "forex-api-endpoint"
organization := "com.starcolon.tao"
description  := "Quick Forex API endpoint playground"
homepage     := Some(url("https://github.com/starcolon/forex-api-endpoint"))

version := "0.0.1-SNAPSHOT"

scalaVersion   := "2.11.8"
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xlint")

val akkaVersion = "2.5.3"
val akkaHttpVersion = "10.0.9"

libraryDependencies ++= Seq(
  "com.typesafe"        % "config" % "1.3.0",
  "org.slf4j"           % "slf4j-nop" % "1.6.4",
  "org.scalaj"          %% "scalaj-http" % "2.3.0",
  "org.json4s"          %% "json4s-native" % "3.6.0-M1",
  "io.spray"            %% "spray-json" % "1.3.3",
  "net.debasishg"       %% "redisclient" % "3.4",
  "com.typesafe.akka"   %% "akka-actor" % akkaVersion,
  "com.typesafe.akka"   %% "akka-stream" % akkaVersion,
  "com.typesafe.akka"   %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka"   %% "akka-http-spray-json" % akkaHttpVersion
)