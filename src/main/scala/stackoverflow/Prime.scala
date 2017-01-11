package stackoverflow


object Prime {
  def main(args: Array[String]): Unit = {
    implicit class SeqUtils[A](seq: Seq[A]) {
      def stoppableFoldLeft[B](acc: B)(f: (B, A) => Option[B]): B = {
        def helper(xs: Seq[A])(acc: B)(f: (B, A) => Option[B]): B = xs match {
          case Seq() => acc
          case Seq(x, xs@_*) =>
            f(acc, x) match {
              case Some(value) =>
                helper(xs)(value)(f)
              case None => acc
            }
        }

        helper(seq)(acc)(f)
      }
    }
  }

  def primes(n: Int): Set[Int] = {
    def helper(current: Int, allPrimes: Set[Int]): Set[Int] = {
      if (current <= n) {
        if (!allPrimes.exists(p => current % p == 0)) {
          helper(current + 1, allPrimes ++ Set(current))
        } else {
          helper(current + 1, allPrimes)
        }
      } else allPrimes
    }

    helper(2, Set.empty[Int])
  }

  def primes1(n: Int): Set[Int] = {
    (2 to n).foldLeft(Set(2)) { (r, c) =>
      if (!r.exists(c % _ == 0)) r + c
      else r
    }
  }

  def myFoo(num: Int, list: List[Int]): Boolean = list match {
    case Nil => false
    case `num` :: xs => true
    case _  => myFoo(num, list.tail)
  }

}
