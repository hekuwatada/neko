package org.neko.json.circe

import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

sealed trait Foo
case class Bar(xs: Vector[String]) extends Foo
case class Qux(i: Int, d: Option[Double]) extends Foo
case class Qux2(i: Int, d: Option[Double]) extends Foo

//@see https://circe.github.io/circe/
object TestFoo extends App {
  val foo: Foo = Qux(13, Some(14.0))
  val bar: Foo = Bar(Vector("a", "b"))
  val qux: Foo = Qux2(1, None)
  val mixedSeq: Seq[Foo] = Seq(foo, bar, qux)

  val json = foo.asJson.noSpaces
  println(json)

  val decodedFoo = decode[Foo](json)
  println(decodedFoo)

  val mixedSeqJson = mixedSeq.asJson.noSpaces
  println(mixedSeqJson)

  val decodedMixedSeq = decode[Seq[Foo]](mixedSeqJson)
  println(decodedMixedSeq)
}
