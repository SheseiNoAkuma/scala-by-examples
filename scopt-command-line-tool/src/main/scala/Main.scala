import scopt.OptionParser

case class Config(foo: Int = 0, bar: Option[String] = None)

object Main extends App {
  val parser = new OptionParser[Config]("DSL tool") {
    override def showUsageOnError: Option[Boolean] = Some(true)

    head("DSL tool", "1.x")

    opt[Int]('f', "foo")
      .required()
      .action((x, c) => c.copy(foo = x))
      .text("foo [required] is an integer property")


    opt[String]('b', "bar")
      .action((x, c) => c.copy(bar = Some(x)))
      .text("bar [optional] is a string property")

    help("help").text("prints this usage text")
  }

  parser.parse(args, Config()) match {
    case Some(config) =>
      println(s"passed configuration $config")


    case None =>
    // arguments are bad, error message will have been displayed
  }
}
