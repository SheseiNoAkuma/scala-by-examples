import scopt.OptionParser

case class Config(username: String = "", detail: Boolean = false, debug: Boolean = false, mode: String = "")

object Main extends App {
  val parser = new OptionParser[Config]("DSL tool") {
    override def showUsageOnError: Option[Boolean] = Some(true)

    head("DSL tool", "1.x")

    cmd("login")
      .action((_, c) => c.copy(mode = "login"))
      .text("Login in crypto wallet")
      .children(
        opt[String]('u', "user")
          .required()
          .action((x, c) => c.copy(username = x))
          .text("username [required] your crypto account username"),
      )

    cmd("list")
      .action((_, c) => c.copy(mode = "list"))
      .text("List all your wallets")
      .children(
        opt[Unit]('d', "detail")
          .action((_, c) => c.copy(detail = true))
          .text("detail [optional] if selected display detail info on your wallets"),
      )

    opt[Unit]("debug")
      .hidden()
      .action((_, c) => c.copy(debug = true))

    help("help").text("prints this usage text")
    checkConfig(c => if (c.mode == "") failure("Please choose an action") else success)
  }

  parser.parse(args, Config()) match {
    case Some(config) =>
      config.mode match {
        case "login" => println(s"login action with config $config")
        case "list" => println(s"list action with config $config")
        case _ =>
      }

    case None =>
    // arguments are bad, error message will have been displayed
  }
}
