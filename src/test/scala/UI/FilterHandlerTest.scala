package UI

import UI.inputParser.InputInfo
import UI.inputParser.handlers.FilterHandler
import models.exporter.FileImageExporter
import models.filters.{BrightnessFilter, FlipFilter, InvertFilter, RotateFilter, ScaleFilter}
import org.scalatest.funsuite.AnyFunSuite

class FilterHandlerTest extends AnyFunSuite {

  test("Valid filter sequence") {
    val info = InputInfo("".split(" "))
    info.filtersAsString = Map(("", 0) -> List("--rotate", "90", "--brightness", "50"))
    val handler = new FilterHandler()
    handler.process(info)
    assert(info.filters.nonEmpty)
    assert(info.filters.head._2.size == 2)
    assert(info.filters.head._2.head.isInstanceOf[RotateFilter])
    assert(info.filters.head._2(1).isInstanceOf[BrightnessFilter])
  }

  test("Unknown filter") {
    val info = InputInfo("".split(" "))
    info.filtersAsString = Map(("", 0) -> List("--invalid", "value"))
    val handler = new FilterHandler()
    assertThrows[IllegalArgumentException] {
      handler.process(info)
    }
  }

  test("Missing value for rotate") {
    val info = InputInfo("".split(" "))
    info.filtersAsString = Map(("", 0) -> List("--rotate"))
    val handler = new FilterHandler()
    assertThrows[IllegalArgumentException] {
      handler.process(info)
    }
  }

  test("Missing value for brightness") {
    val info = InputInfo("".split(" "))
    info.filtersAsString = Map(("", 0) -> List("--brightness"))
    val handler = new FilterHandler()
    assertThrows[IllegalArgumentException] {
      handler.process(info)
    }
  }

  test("Flip filter x-axis") {
    val info = InputInfo("".split(" "))
    info.filtersAsString = Map(("", 0) -> List("--flip", "x"))
    val handler = new FilterHandler()
    handler.process(info)
    assert(info.filters.nonEmpty)
    assert(info.filters.head._2.size == 1)
    assert(info.filters.head._2.head.isInstanceOf[FlipFilter])
  }

  test("Flip filter y-axis") {
    val info = InputInfo("".split(" "))
    info.filtersAsString = Map(("", 0) -> List("--flip", "y"))
    val handler = new FilterHandler()
    handler.process(info)
    assert(info.filters.nonEmpty)
    assert(info.filters.head._2.size == 1)
    assert(info.filters.head._2.head.isInstanceOf[FlipFilter])
  }


  test("Invalid flip value") {
    val info = InputInfo("".split(" "))
    info.filtersAsString = Map(("", 0) -> List("--flip", "z"))
    val handler = new FilterHandler()
    assertThrows[IllegalArgumentException] {
      handler.process(info)
    }
  }

  test("Valid scale filter") {
    val info = InputInfo("".split(" "))
    info.filtersAsString = Map(("", 0) -> List("--scale", "1.5"))
    val handler = new FilterHandler()
    handler.process(info)
    assert(info.filters.nonEmpty)
    assert(info.filters.head._2.size == 1)
    assert(info.filters.head._2.head.isInstanceOf[ScaleFilter])
  }

  test("Missing value for scale") {
    val info = InputInfo("".split(" "))
    info.filtersAsString = Map(("", 0) -> List("--scale"))
    val handler = new FilterHandler()
    assertThrows[IllegalArgumentException] {
      handler.process(info)
    }
  }

  test("Invert filter") {
    val info = InputInfo("".split(" "))
    info.filtersAsString = Map(("", 0) -> List("--invert"))
    val handler = new FilterHandler()
    handler.process(info)
    assert(info.filters.nonEmpty)
    assert(info.filters.head._2.size == 1)
    assert(info.filters.head._2.head.isInstanceOf[InvertFilter])
  }

  test("File output with valid file name") {
    val info = InputInfo("--brightness 50 --output-file \"output.txt\"".split(" "))
    info.filtersAsString = Map(("output.txt", 0) -> List("--brightness", "50"))
    val handler = new FilterHandler()
    handler.process(info)
    assert(info.filters.nonEmpty)
    assert(info.filters.head._1._1.isInstanceOf[FileImageExporter])
    assert(info.filters.head._2.head.isInstanceOf[BrightnessFilter])
  }
}