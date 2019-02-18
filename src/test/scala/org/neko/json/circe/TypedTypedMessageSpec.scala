package org.neko.json.circe

import java.util.UUID

import io.circe.generic.extras.Configuration
import org.scalatest.{FunSpec, Matchers}

class TypedTypedMessageSpec extends FunSpec with Matchers {
  // implicits required for encoding and decoding
  import io.circe.generic.extras.auto._
  import io.circe.parser._
  import io.circe.syntax._

  implicit val genDevConfig: Configuration = Configuration.default.withDiscriminator("_type")

  val emptyMsg: TypedMessage = EmptyMessage
  val uuidMsg = UuidMessage(UUID.randomUUID())
  val anotherUuidMsg = AnotherUuidMessage(UUID.randomUUID())
  val someMsg: TypedMessage = OptionMessage(Some(1))
  val noMsg: TypedMessage = OptionMessage(None)
  val colMsg: TypedMessage = ColMessage(List("a", "b"), Seq("x"))
  val emptyColMsg: TypedMessage = ColMessage(List(), Seq())
  val mapMsg: TypedMessage = MapMessage(Map("x" -> 9))
  val emptyMapMsg: TypedMessage = MapMessage(Map())
//  val errorMsg: TypedMessage = ErrorMessage(new RuntimeException("test error"))

  describe("encoding") {
    it("encodes case object as base type") {
      emptyMsg.asJson.noSpaces shouldBe """{"_type":"EmptyMessage"}"""
    }

    it("encodes case class with UUID as base type") {
      (uuidMsg: TypedMessage).asJson.noSpaces shouldBe s"""{"id":"${uuidMsg.id}","_type":"UuidMessage"}"""
    }

    it("encodes case class with the same type as another case class as base type") {
      (anotherUuidMsg: TypedMessage).asJson.noSpaces shouldBe s"""{"id":"${anotherUuidMsg.id}","_type":"AnotherUuidMessage"}"""
    }

    it("encodes case class with Option as base type") {
      someMsg.asJson.noSpaces shouldBe """{"opt":1,"_type":"OptionMessage"}"""
      noMsg.asJson.noSpaces shouldBe """{"opt":null,"_type":"OptionMessage"}"""
    }

    it("encodes case class with collection as base type") {
      colMsg.asJson.noSpaces shouldBe """{"list":["a","b"],"seq":["x"],"_type":"ColMessage"}"""
      emptyColMsg.asJson.noSpaces shouldBe """{"list":[],"seq":[],"_type":"ColMessage"}"""
    }

    it("encodes case class with Map as base type") {
      mapMsg.asJson.noSpaces shouldBe """{"map":{"x":9},"_type":"MapMessage"}"""
      emptyMapMsg.asJson.noSpaces shouldBe """{"map":{},"_type":"MapMessage"}"""
    }
  }

  describe("decoding") {
    it("decodes typed json as case object") {
      decode[TypedMessage]("""{"_type":"EmptyMessage"}""") shouldBe Right(emptyMsg)
    }

    it("decodes typed json as case class with UUID") {
      decode[TypedMessage](s"""{"id":"${uuidMsg.id}","_type":"UuidMessage"}""") shouldBe Right(uuidMsg)
    }

    it("decodes typed json as case class with the same type as another case class") {
      decode[TypedMessage](s"""{"id":"${anotherUuidMsg.id}","_type":"AnotherUuidMessage"}""") shouldBe Right(anotherUuidMsg)
    }

    it("decodes typed json as case class with Option") {
      decode[TypedMessage]("""{"opt":1,"_type":"OptionMessage"}""") shouldBe Right(someMsg)
      decode[TypedMessage]("""{"opt":null,"_type":"OptionMessage"}""") shouldBe Right(noMsg)
      decode[TypedMessage]("""{"_type":"OptionMessage"}""") shouldBe Right(noMsg)
    }

    it("decodes typed json as case class with collections") {
      decode[TypedMessage]("""{"list":["a","b"],"seq":["x"],"_type":"ColMessage"}""") shouldBe Right(colMsg)
      decode[TypedMessage]("""{"list":[],"seq":[],"_type":"ColMessage"}""") shouldBe Right(emptyColMsg)
    }

    it("decodes typed json as case class with Map") {
      decode[TypedMessage]("""{"map":{"x":9},"_type":"MapMessage"}""") shouldBe Right(mapMsg)
      decode[TypedMessage]("""{"map":{},"_type":"MapMessage"}""") shouldBe Right(emptyMapMsg)
    }
  }
}
