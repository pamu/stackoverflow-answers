package stackoverflow

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * Created by pnagarjuna on 03/11/16.
  */
object PlayJsonProblems {

  case class Hello(name: String)


  def foo = {
    case class TheResult(result: (String, String))

    implicit val theScheduledReads: Reads[TheResult] = (
      (__ \ "code").read[String] and
        (__ \ "number").read[String] tupled) map TheResult.apply _

    case class ResultList(addr: List[TheResult])

    implicit val resultReads: Reads[ResultList] =
      (__ \ "scheduled").read[List[TheResult]] map ResultList.apply _

  }
}
