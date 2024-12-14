package exporters

import models.exporter.ConsoleImageExporter
import models.image.AsciiImage
import org.scalatest.funsuite.AnyFunSuite

class ConsoleImageExporterTest extends AnyFunSuite {

  test("ASCII image to the console") {
    val exporter = new ConsoleImageExporter()
    val image = new AsciiImage(List("ASCII content".toList))
    val result = exporter.exportImage(image)
    assert(result == image.stringRepresentation)
  }

  test("Empty ASCII image content") {
    val exporter = new ConsoleImageExporter()
    val image = new AsciiImage(List.empty[List[Char]])
    val result = exporter.exportImage(image)
    assert(result == image.stringRepresentation)
  }

  test("ASCII image with special characters") {
    val exporter = new ConsoleImageExporter()
    val image = new AsciiImage(List("ASCII content with special characters: @#$%^&*()".toList))
    val result = exporter.exportImage(image)
    assert(result == image.stringRepresentation)
  }

  test("ASCII image with multiple lines") {
    val exporter = new ConsoleImageExporter()
    val image = new AsciiImage("Line 1\nLine 2\nLine 3".split("\n").map(_.toList).toList)
    val result = exporter.exportImage(image)
    assert(result == image.stringRepresentation)
  }
}