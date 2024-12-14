package UI

import UI.inputParser.ConsoleParser
import org.scalatest.funsuite.AnyFunSuite

class ConsoleParserTest extends AnyFunSuite {

  test("Valid image path and filters") {
    val parser = new ConsoleParser("--image \"/path/to/image.png\" --rotate 90 --brightness 50 --output-console".split(" "))
    val result = parser.parseInput()
    assert(result.nonEmpty)
    assert(result.head._1 == ("", 0))
    assert(result.head._2 == List("--rotate", "90", "--brightness", "50"))
  }

  test("Image path is missing") {
    val parser = new ConsoleParser("--rotate 90 --brightness 50".split(" "))
    assertThrows[IllegalArgumentException] {
      parser.parseInput()
    }
  }

  test("Random image path") {
    val parser = new ConsoleParser("--image-random --rotate 90 --output-console".split(" "))
    val result = parser.parseInput()
    assert(result.nonEmpty)
    assert(result.head._1 == ("", 0))
    assert(result.head._2 == List("--rotate", "90"))
  }

  test("Multiple filters and outputs") {
    val parser = new ConsoleParser("--image \"/path/to/image.png\" --rotate 90 --output-console --brightness 50 --output-file \"output.txt\"".split(" "))
    val result = parser.parseInput()
    assert(result.size == 2)
    assert(result.head._1 == ("", 0))
    assert(result.head._2 == List("--rotate", "90"))
    assert(result("output.txt",1) == List("--brightness", "50"))
  }

  test("Unrecognized parameters") {
    val parser = new ConsoleParser("--image \"/path/to/image.png\" --unknown-param value --output-console".split(" "))
    assertThrows[IllegalArgumentException] {
      val result = parser.parseInput()
    }
  }
}