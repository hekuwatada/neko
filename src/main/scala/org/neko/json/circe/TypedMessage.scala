package org.neko.json.circe

import java.util.UUID

sealed trait TypedMessage

case object EmptyMessage extends TypedMessage
case class UuidMessage(id: UUID) extends TypedMessage
case class AnotherUuidMessage(id: UUID) extends TypedMessage
case class OptionMessage(opt: Option[Int]) extends TypedMessage
case class AnotherOptionMessage(opt: Option[Int]) extends TypedMessage
case class ColMessage(list: List[String], seq: Seq[String]) extends TypedMessage
case class MapMessage(map: Map[String, Int]) extends TypedMessage
//case class ErrorMessage(ex: Throwable) extends TypedMessage //TODO: adding Throwable makes encoding fail