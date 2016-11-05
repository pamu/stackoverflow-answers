package stackoverflow

object Strings {
  def main(args: Array[String]): Unit = {

    def check(a: String, b: String): Boolean = {
      if (Math.abs(a.length - b.length) > 1) false
      else {
        def countMismatches(aIndex: Int, bIndex: Int, mismatchCount: Int): Int = {
          if (bIndex < Math.max(a.length, b.length) && aIndex < Math.min(a.length, b.length)) {
            val smallerStr = if (a.length < b.length) a else b
            val largerStr = if (a.length > b.length) a else b
            if (smallerStr(aIndex) != largerStr(bIndex)) {
              if (mismatchCount > 1) {
                mismatchCount
              }
              else
              countMismatches(aIndex, bIndex + 1, mismatchCount + 1)
            }
            else countMismatches(aIndex + 1, bIndex + 1, mismatchCount)
          } else mismatchCount
        }
        countMismatches(0, 0, 0) <= 1
      }
    }

    println(check("appzle", "apple"))
  }

  import scala.annotation.tailrec

  def checkExtraChar(source: String, target: String): Boolean = {
    val sourceLength = source.length
    val targetLength = target.length

    // Assumption :: source.length == target.length + 1
    @tailrec
    def _check(srcIndex: Int, tgtIndex: Int, mismatchFound: Boolean): Boolean = srcIndex match {
      case index if index == sourceLength => true
      case _ => (source(srcIndex) == target(tgtIndex), mismatchFound) match {
        case (true, _) => _check(srcIndex + 1, tgtIndex + 1, mismatchFound)
        case (false, false) => _check(srcIndex + 1, tgtIndex, true)
        case (false, true) => false
      }
    }

    (sourceLength == targetLength + 1) match {
      case false => false
      case true => _check(0, 0, false)
    }

  }

  def check(a: String, b: String): Boolean = {
    val smallerStr = if (a.length < b.length) a else b
    val largerStr = if (a.length > b.length) a else b

    if (largerStr.length - smallerStr.length > 1) false
    else {
      def countMismatches(aIndex: Int, bIndex: Int, mismatchCount: Int): Int = {

        if (bIndex < largerStr.length && aIndex < smallerStr.length) {

          if (smallerStr(aIndex) != largerStr(bIndex)) {
            if (mismatchCount > 1) mismatchCount
            else countMismatches(aIndex, bIndex + 1, mismatchCount + 1)
          }
          else countMismatches(aIndex + 1, bIndex + 1, mismatchCount)

        } else mismatchCount
      }

      countMismatches(0, 0, 0) <= 1
    }
  }
}
