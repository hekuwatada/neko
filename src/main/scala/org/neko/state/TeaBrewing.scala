//package org.neko.state
//
//import cats.Eval
//import cats.data.IndexedStateT
//
//
//object TeaBrewing {
//
//  sealed trait TeapotState
//  case object Empty extends TeapotState
//  case object WithTeaLeaves extends TeapotState
//  case object Brewing extends TeapotState
//  case object Brewed extends TeapotState
//
//  case class Teapot(state: TeapotState)
//  case class Tea()
//
//  def addTeaLeaves: IndexedStateT[Eval, Empty.type, WithTeaLeaves.type, Unit]=
//    IndexedStateT.set(WithTeaLeaves)
//
//  def addHotWater: IndexedStateT[Eval, WithTeaLeaves.type, Brewing.type, Unit] =
//    IndexedStateT.set(Brewing)
//
//  def countDown: IndexedStateT[Eval, Brewing.type, Brewed.type, Unit] =
//    IndexedStateT.set(Brewed)
//
//  def serve: IndexedStateT[Eval, Brewed.type, Empty.type, Tea] = ???
////    IndexedStateT.apply((_: Brewed.type) => Eval.now(Empty, Tea()))
////    IndexedStateT.applyF(Eval.now((_: Brewed.type) => Eval.now((Empty, Tea()))))
//
//  val teaBrewingInstructions = for {
//    _ <- addTeaLeaves
//    _ <- addHotWater
//    _ <- countDown
//    tea: Tea <- serve
//  } yield tea
//
//  val (finalTeapotState, tea): (Empty.type, Tea) = teaBrewingInstructions.run(Empty).value
//
//}