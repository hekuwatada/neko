package org.neko.io

import cats.effect.IO

//@see https://typelevel.org/blog/2017/05/02/io-monad-for-cats.html
object IoMonad extends App {

  val program: IO[Unit] = for {
    _ <- IO { println("please type") }
    input <- IO { scala.io.StdIn.readLine }
    _ <- IO { println(s"received: $input")}
  } yield ()

  program.unsafeRunSync()
}
