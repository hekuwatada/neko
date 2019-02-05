package org.neko.free

sealed trait Free[F[_], A] {

  def flatMap[B](f: A => Free[F, B]): Free[F, B] = this match {
    case Return(a) => f(a)
    case FlatMap(sub, cont) => FlatMap(sub, cont andThen (_ flatMap f))
  }

  def map[B](f: A => B): Free[F, B] = flatMap(a => Return(f(a)))

}

case class Return[F[_], A](a: A) extends Free[F, A]

case class FlatMap[F[_], I, A](sub: F[I], cont: I => Free[F, A]) extends Free[F, A]