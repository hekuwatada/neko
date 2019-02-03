package org.neko.io

import cats.effect.IO

object IoMonad extends App {

  val program: IO[Unit] = for {
    _ <- IO { println("hello") }
  } yield ()

  program.unsafeRunSync()
}
