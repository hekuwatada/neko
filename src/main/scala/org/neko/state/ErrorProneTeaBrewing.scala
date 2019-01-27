package org.neko.state

import cats.Eval
import cats.data.{IndexedStateT, State}

object ErrorProneTeaBrewing {

  sealed trait TeapotState
  case object Empty extends TeapotState
  case object WithTeaLeaves extends TeapotState
  case object Brewing extends TeapotState
  case object Brewed extends TeapotState

  case class Teapot(state: TeapotState)
  case class Tea()

  def addTeaLeaves: State[TeapotState, Unit] = State.set(WithTeaLeaves)

  def addHotWater: State[TeapotState, Unit] = State.set(Brewing)

  def countDown: State[TeapotState, Unit] = State.set(Brewed)

  def serve: State[TeapotState, Tea] =
    State(_ => (Empty, Tea()))


  val teaBrewingInstructions: IndexedStateT[Eval, TeapotState, TeapotState, Tea] = for {
    _ <- addTeaLeaves
    _ <- addHotWater
    _ <- countDown
    tea <- serve
  } yield tea

  val (finalTeapotState, tea)  = teaBrewingInstructions.run(Empty).value

}