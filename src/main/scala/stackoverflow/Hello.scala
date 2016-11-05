package stackoverflow

import scala.util.{Failure, Success, Try}

object Hello {
  def main(args: Array[String]): Unit = {
    val list: List[Try[String]] = List(Success("apple"), Failure(new Exception("foo")))

    list.foldLeft(List.empty[String] -> List.empty[Throwable]) { (r, c) =>
      val (a, b) = r
      c match {
        case Success(value) => (a ++ List(value)) -> b
        case Failure(ex) => a -> (b ++ List(ex))
      }
    }

  }

  def series(n: Int): Double = {
    n match {
      case 1 => 3
      case 2 => 5
      case _ =>
        Math.pow(-1, n) * 5 * series(n - 1) + Math.pow(-1, n - 1) * 3 * series(n - 2)
    }
  }

  def fooSeries(n: Int): Double = {
    @scala.annotation.tailrec
    def helper(counter: Int, current: Double, previous: Double): Double = {
      n match {
        case 1 => previous
        case 2 => current
        case _ =>
          if (counter > n) current
          else
            helper(counter + 1, Math.pow(-1, counter) * 5 * current + Math.pow(-1, counter - 1) * 3 * previous, current)
      }
    }
    helper(3, 5, 3)
  }

  def satisfiesAllIt(x: List[Any], test: Any => Boolean): Boolean = {
    @scala.annotation.tailrec
    def helper(satisfies: Int, xs: List[Any]): Boolean = xs match {
      case Nil => satisfies == x.length
      case x :: xs =>
        if (test(satisfies))
          helper(satisfies + 1, xs)
        else helper(satisfies, xs)
    }
    helper(0, x)
  }

}
