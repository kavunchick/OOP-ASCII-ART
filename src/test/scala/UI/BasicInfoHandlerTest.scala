package UI

import UI.inputParser.InputInfo
import UI.inputParser.handlers.BasicInfoHandler
import org.scalatest.funsuite.AnyFunSuite

class BasicInfoHandlerTest extends AnyFunSuite {

  test("ImagePath to 'random' when '--image-random' is present") {
    val info = InputInfo("--image-random".split(" "))
    val handler = new BasicInfoHandler()
    handler.process(info)
    assert(info.imagePath == "random")
  }

  test("Valid image path") {
    val info = InputInfo("--image \"/path/to/image.png\"".split(" "))
    val handler = new BasicInfoHandler()
    handler.process(info)
    assert(info.imagePath == "/path/to/image.png")
  }

  test("Unsupported image formats") {
    val info = InputInfo("--image \"/path/to/image.bmp\"".split(" "))
    val handler = new BasicInfoHandler()
    assertThrows[IllegalArgumentException] {
      handler.process(info)
    }
  }

  test("AsciiTable '--table'") {
    val info = InputInfo("--table \"linear\"".split(" "))
    val handler = new BasicInfoHandler()
    handler.process(info)
    assert(info.asciiTable == "linear")
  }

  test("Ignore unrecognized parameters") {
    val info = InputInfo("--unknown value".split(" "))
    val handler = new BasicInfoHandler()
    handler.process(info)
    assert(info.params.sameElements(Array("--unknown", "value")))
  }

  test("Remove processed parameters from info.params") {
    val info = InputInfo("--image-random --table \"linear\"".split(" "))
    val handler = new BasicInfoHandler()
    handler.process(info)
    assert(info.params.isEmpty)
  }

  test("Several images paths") {
    val info = InputInfo("--image \"/path/to/image.png\" --image \"/path/to/another_image.png\"".split(" "))
    val handler = new BasicInfoHandler()
    assertThrows[IllegalArgumentException] {
      handler.process(info)
    }
  }

  test("Image path and random image") {
    val info = InputInfo("--image \"/path/to/image.png\" --image-random".split(" "))
    val handler = new BasicInfoHandler()
    assertThrows[IllegalArgumentException] {
      handler.process(info)
    }
  }

  test("Unsupported table type") {
    val info = InputInfo("--table \"wawazand\"".split(" "))
    val handler = new BasicInfoHandler()
    assertThrows[IllegalArgumentException] {
      handler.process(info)
    }
  }
}
