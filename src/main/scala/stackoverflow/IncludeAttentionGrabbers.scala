package stackoverflow

import apple.laf.JRSUIUtils.Tree

object IncludeAttentionGrabbers {


  implicit class ListUtils(list: List[Int]) {
    def takeSilimarElementsWhileIgnoring(elem: Int): List[Int] = {
      def helper(currentList: List[Int])(lastElem: Int)(result: List[Int]): List[Int] = currentList match {
        case Nil => result
        case `elem` :: xs =>
        case x :: xs =>

      }
    }
  }

  def foo(list: List[List[Int]]): List[List[Int]] = {
    def helper(currentList: List[Int], result: List[List[Int]]): List[List[Int]] = currentList match {
      case Nil => result
      case xList if xList.nonEmpty =>
        val xs = currentList.takeSilimarElementsWhileIgnoring(8)
        println(xs)
        helper(currentList.drop(xs.length), result ::: List(xs))
    }
    list.flatMap(helper(_, List.empty[List[Int]]))
  }




}
