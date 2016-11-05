package stackoverflow


object CaseClasses {
  def main(args: Array[String]): Unit = {
    case class Command(name: String)

    case class Project(user:String, sessionId : String, fileOpen : String, commands: List[Command]) {
      override def equals(obj: scala.Any): Boolean = obj match {
        case obj: Project => this.user == obj.user && this.sessionId == obj.sessionId && this.fileOpen == obj.fileOpen
        case _ => false
      }

      override def hashCode(): Int = 1013 * user.hashCode + 1009 * sessionId.hashCode + 1003 * fileOpen.hashCode
    }

  }
}
