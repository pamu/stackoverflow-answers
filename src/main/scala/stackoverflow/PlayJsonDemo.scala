package stackoverflow

import play.api.libs.json._


object PlayJsonDemo {

  case class Record(quantita: Int, citta: String, genere: String)

  object Record {
    import play.api.libs.functional.syntax._
    implicit val recordReads: Reads[Record] = (
      (JsPath \ "Quantita").read[Int] and
        (JsPath \ "Citta").read[String] and
        (JsPath \ "GENERE").read[String]
      )(Record.apply _)
  }


  def main(args: Array[String]): Unit = {
    val parsedJson =
    Json.parse(
      """
        |[
        |  {
        |    "Quantita": 6,
        |    "Citta": "BARI",
        |    "GENERE": "Avventura"
        |  },
        |
        |  {
        |    "Quantita": 30,
        |    "Citta": "BARI",
        |    "GENERE": "Storia"
        |  },
        |  {
        |    "Quantita": 6,
        |    "Citta": "MODUGNO",
        |    "GENERE": "Avventura"
        |  },
        |
        |  {
        |    "Quantita": 6,
        |    "Citta": "MODUGNO",
        |    "GENERE": "Storia"
        |  },
        |  {
        |    "Quantita": 8,
        |    "Citta": "MODUGNO",
        |    "GENERE": "Avventura"
        |  }]
        |
      """.stripMargin)
    parsedJson.validate[List[Record]] match {
      case JsSuccess(value, _) =>
        value.map { record =>
          s"""[${record.citta},${record.genere},${record.quantita}]\n"""
        }.mkString("[", ",", "]")
      case JsError(_) => println("")
    }
  }
}
