package stackoverflow

/**
  * Created by pnagarjuna on 16/10/16.
  */
object Options {
  def main(args: Array[String]): Unit = {

    val options = List(Some(1), Some(2), Some(3))

    def process[A, B](options: List[Option[A]])(f: (Int, Option[A]) => Option[B]): List[B] = {
      val result =
        options.foldLeft(List.empty[B] -> 0) { (r, c) =>
          val (result, index) = r
          f(index, c).map(result ++ List(_) -> (index + 1)).getOrElse(result -> (index + 1))
        }
      if (result._1.length == options.length) result._1 else List.empty[B]
    }

    process[Int, Int](options) { (index, current) =>
      current.orElse {
        println(s"$index is none.")
        current
      }
    }.foreach(println)

  }
}
