package izumi.functional.bio

import izumi.functional.bio.BIOExit.ZIO
import zio.{Fiber, IO}

trait BIOFiber[F[_, _], E, A] {
  def join: F[E, A]

  def observe: F[Nothing, BIOExit[E, A]]

  def interrupt: F[Nothing, BIOExit[E, A]]
}

object BIOFiber {
  def fromZIO[E, A](f: Fiber[E, A]): BIOFiber[IO, E, A] =
    new BIOFiber[IO, E, A] with ZIO {
      override val join: IO[E, A] = f.join
      override val observe: IO[Nothing, BIOExit[E, A]] = f.await.map(toBIOExit[E, A])
      override def interrupt: IO[Nothing, BIOExit[E, A]] = f.interrupt.map(toBIOExit[E, A])
    }
}