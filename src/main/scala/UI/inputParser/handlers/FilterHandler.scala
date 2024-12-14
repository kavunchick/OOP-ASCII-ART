package UI.inputParser.handlers

import UI.inputParser.InputInfo
import models.exporter.{ConsoleImageExporter, FileImageExporter, ImageExporter}
import models.filters.*

/**
 * FilterHandler class is responsible for handling the filters
 */
class FilterHandler extends Handler {

  override def process(info: InputInfo): Unit =
    for ((outputSpec, commands) <- info.filtersAsString) {
      val filterList = parseFilters(commands)
      val output = createImageExporter(outputSpec._1)
      info.filters += ((output, outputSpec._2) -> filterList)
    }

  /**
   * Parse the filters from the commands
   *
   * @param commands sequence of commands which represent the filters
   * @return list of filters
   */
  private def parseFilters(commands: Seq[String]): List[Filter] = {
    var filterList: List[Filter] = List()
    var i = 0
    while (i < commands.length) {
      commands(i) match {
        case "--rotate" =>
          filterList :+= requireNext(commands,i + 1,"--rotate requires a value.") { value =>new RotateFilter(value.toInt) }
          i += 1
        case "--flip" =>
          filterList :+= requireNext(commands,i + 1,"--flip requires 'x' or 'y' as a value.") {
            case "x" => new FlipFilter(0)
            case "y" => new FlipFilter(1)
            case invalid =>
              throw new IllegalArgumentException(
                s"Invalid flip direction: $invalid")
          }
          i += 1
        case "--invert" => filterList :+= new InvertFilter()
        case "--brightness" => filterList :+= requireNext(commands,i + 1,"--brightness requires a value.") {
          value =>new BrightnessFilter(value.toInt)
          }
          i += 1

        case "--scale" => filterList :+= requireNext(commands,i + 1,"--scale requires a value.") {
          value =>  new ScaleFilter(value.toFloat)
          }
          i += 1
        case cmd if cmd.startsWith("--") =>
          throw new IllegalArgumentException(s"Unknown filter command: $cmd")
        case _ =>
      }
      i += 1
    }
    filterList
  }

  private def requireNext[T](
    commands: Seq[String],
    index: Int,
    errorMsg: String)(parser: String => T): T = {
    if (index >= commands.length) throw new IllegalArgumentException(errorMsg)
    parser(commands(index))
  }

  private def createImageExporter(path: String): ImageExporter =
    if (path.isEmpty) new ConsoleImageExporter
    else new FileImageExporter(path)
}
