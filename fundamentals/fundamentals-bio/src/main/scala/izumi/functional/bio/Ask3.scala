package izumi.functional.bio

import izumi.reflect.Tag

trait Ask3[FR[-_, +_, +_]] extends RootTrifunctor[FR] {
  def InnerF: Applicative3[FR]
  def ask[R : Tag]: FR[R, Nothing, R]

  // defaults
  def askWith[R, A](f: R => A): FR[R, Nothing, A] = InnerF.map[R, Nothing, R, A](ask)(f)
}
