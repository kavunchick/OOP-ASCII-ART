package UI.inputParser

import UI.inputParser.handlers.{BasicInfoHandler, FilterHandler, OutputHandler}

/**
 * ConsoleParser class is responsible for parsing the input
 *
 * @param input string which represent the input
 */
class ConsoleParser(input: Array[String]) {
  val inputInfo: InputInfo = InputInfo(input)

    /**
     * Parse the input
     *
     * @return map where the key is the output specification and the value is the list of filters
     */
  def parseInput(): Map[(String, Int), List[String]] = {
    val handlerSeq =
      Seq(new BasicInfoHandler, new OutputHandler, new FilterHandler)
    handlerSeq.foreach(handler => handler.process(inputInfo))
    if (inputInfo.imagePath == "")
      throw new IllegalArgumentException("Image path is required.")
    inputInfo.filtersAsString
  }
}
