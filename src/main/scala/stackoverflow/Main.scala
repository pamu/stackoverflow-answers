package stackoverflow


object Main {
  def main(args: Array[String]): Unit = {
    import dispatch._
    import Defaults._
    val svc@dispatch.Req(run, props) = url("http://api.hostip.info/country.php")
    val country: Future[String] = Http(svc OK as.String)
  }
}
