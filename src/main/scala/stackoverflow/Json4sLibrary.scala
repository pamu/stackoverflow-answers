package stackoverflow

import org.json4s._
import org.json4s.native.JsonMethods._

object Json4sLibrary {

  def convert(jsonString: String): String = {
    val parsedJson = parse(jsonString)
    parsedJson.children.map {
      case JObject(list) =>
        val map = list.toMap
        val JString(citta) = map("Citta")
        val JString(genere) = map("GENERE")
        val JInt(quantita) = map("Quantita")
        List(citta, genere, quantita).map(str => s"'$str'").mkString("[", ",", "]\n")
      case _ => List.empty[String]
    }.mkString("[", ",", "]")
  }

  def main(args: Array[String]): Unit = {
    println(convert(
      """
        | [
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
      """.stripMargin))
  }
}
