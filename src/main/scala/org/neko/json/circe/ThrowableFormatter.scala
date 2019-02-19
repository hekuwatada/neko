package org.neko.json.circe

import io.circe.{Encoder, Json}

//TODO: use Encoder[String].contramap { _.toString }
object StackTraceElementEncoder extends Encoder[StackTraceElement] {
  import io.circe.syntax._

  override def apply(a: StackTraceElement): Json = a.toString.asJson
}

//TODO: use new Encoder[Throwable] @see https://circe.github.io/circe/codecs/custom-codecs.html
object ThrowableEncoder extends Encoder[Throwable] {
  import io.circe.syntax._

  implicit val stackTraceElementEncoder: Encoder[StackTraceElement] = StackTraceElementEncoder

  override def apply(a: Throwable): Json =
    Json.obj(
      "message" -> a.getMessage.asJson,
      "class" -> a.getClass.getName.asJson,
      "stacktrace" -> a.getStackTrace.toVector.asJson
    )
}

object ThrowableFormatter {
  object Implicits {
    implicit val throwableEncoder: Encoder[Throwable] = ThrowableEncoder
  }
}
