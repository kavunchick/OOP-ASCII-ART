package UI.inputParser.handlers

import UI.inputParser.InputInfo

import scala.util.matching.Regex

class OutputHandler extends Handler {
  private val outputRegex: Regex = "--output-(\\w+)".r

  /**
   * Process the information about the output
   */
  override def process(info: InputInfo): Unit = {
    var outputCount = 0
    var currentCommands: List[String] = List()
    var deleteNext = false
    info.params.zipWithIndex.foreach { case (param, i) =>
      param match {
        case outputRegex(word) =>
          word match {
            case "console" => info.filtersAsString += (("", outputCount) -> currentCommands)
            case "file" =>
              if (i + 1 < info.params.length && !info.params(i + 1).startsWith("--")) {
                val fileName = info.params(i + 1).replace("\"", "")
                info.filtersAsString += ((fileName, outputCount) -> currentCommands)
                deleteNext = true
              } else {
                throw new IllegalArgumentException("Output type 'file' requires a file name or path.")
              }
            case _ => throw new IllegalArgumentException(s"Unknown output type '$word'.")
          }
          currentCommands = List()
          outputCount += 1
        case other =>
          if (deleteNext) {
            deleteNext = false
          } else {
            currentCommands :+= other
          }
      }
    }
  }
}