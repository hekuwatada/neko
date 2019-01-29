package org.neko.json.circe

import java.util.UUID

import io.circe

sealed trait Event {
  val id: UUID
}
case class TaskCreated(id: UUID) extends Event
case class TaskStarted(id: UUID) extends Event
case class TaskFinished(id: UUID, result: Int) extends Event

object TestEvent extends App {
  val eventId = UUID.randomUUID()
  val events: Seq[Event] = Seq(
    TaskCreated(eventId),
    TaskStarted(eventId),
    TaskFinished(eventId, 42)
  )

  println(events)

  def encode(events: Seq[Event]): String = {
    import io.circe.syntax._
    import io.circe.generic.encoding.DerivedObjectEncoder._

    val eventsJson = events.asJson.noSpaces
    println(eventsJson)
    eventsJson
  }

  def _decode(encoded: String): Either[circe.Error, Seq[Event]] = {
    import io.circe.parser._
    import io.circe.generic.decoding.DerivedDecoder._

    val decodedEvents = decode[Seq[Event]](encoded)
    println(decodedEvents)
    decodedEvents
  }

  _decode(encode(events))
}
