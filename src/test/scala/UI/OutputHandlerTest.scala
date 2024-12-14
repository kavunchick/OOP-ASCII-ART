package UI

import UI.inputParser.InputInfo
import UI.inputParser.handlers.OutputHandler
import org.scalatest.funsuite.AnyFunSuite

class OutputHandlerTest extends AnyFunSuite {

  test("Console output") {
    val info = InputInfo("--rotate 90 --output-console".split(" "))
    val handler = new OutputHandler()
    handler.process(info)
    assert(info.filtersAsString.nonEmpty)
    assert(info.filtersAsString.head._1 == ("", 0))
    assert(info.filtersAsString.head._2 == List("--rotate", "90"))
  }

  test("File output with valid file name") {
    val info = InputInfo("--brightness 50 --output-file \"output.txt\"".split(" "))
    val handler = new OutputHandler()
    handler.process(info)
    assert(info.filtersAsString.nonEmpty)
    assert(info.filtersAsString.head._1 == ("output.txt", 0))
    assert(info.filtersAsString.head._2 == List("--brightness", "50"))
  }

  test("Missing file name") {
    val info = InputInfo("--output-file".split(" "))
    val handler = new OutputHandler()
    assertThrows[IllegalArgumentException] {
      handler.process(info)
    }
  }

  test("Unknown output type") {
    val info = InputInfo("--output-unknown".split(" "))
    val handler = new OutputHandler()
    assertThrows[IllegalArgumentException] {
      handler.process(info)
    }
  }

  test("Multiple outputs") {
    val info = InputInfo("--rotate 90 --output-file \"output.txt\" --brightness 50 --output-console".split(" "))
    val handler = new OutputHandler()
    handler.process(info)
    assert(info.filtersAsString.size == 2)
    assert(info.filtersAsString.head._1 == ("output.txt", 0))
    assert(info.filtersAsString.head._2 == List("--rotate", "90"))
    assert(info.filtersAsString("", 1) == List("--brightness", "50"))
  }

}
