package exporters

import models.exporter.FileImageExporter
import models.image.{AsciiImage, Image}
import org.scalatest.funsuite.AnyFunSuite

import java.nio.file.{Files, Paths}
import java.nio.charset.StandardCharsets

class FileImageExporterTest extends AnyFunSuite {

 test("Create file and write image content") {
  val path = "test_output/new_image_file.txt"
  val exporter = new FileImageExporter(path)
  val image = new AsciiImage("ASCII Image Content".split("\n").map(_.toList).toList)
  exporter.exportImage(image)
  assert(Files.exists(Paths.get(path)))
  assert(Files.readString(Paths.get(path), StandardCharsets.UTF_8) == image.stringRepresentation)
  Files.deleteIfExists(Paths.get(path))
}

  test("Overwrite existing file") {
    val path = "test_output/existing_image_file.txt"
    Files.write(Paths.get(path), "Old Content".getBytes(StandardCharsets.UTF_8))
    val exporter = new FileImageExporter(path)
    val image = new AsciiImage("ASCII Image Content".split("\n").map(_.toList).toList)
    exporter.exportImage(image)
    assert(Files.readString(Paths.get(path), StandardCharsets.UTF_8) == image.stringRepresentation)
    Files.deleteIfExists(Paths.get(path))
  }

  test("Create parent directories and write image content") {
    val path = "test_output/new_image_dir/new_image_file.txt"
    val exporter = new FileImageExporter(path)
    val image = new AsciiImage("ASCII Image Content".split("\n").map(_.toList).toList)
    exporter.exportImage(image)
    assert(Files.exists(Paths.get(path)))
    assert(Files.readString(Paths.get(path), StandardCharsets.UTF_8) == image.stringRepresentation)
    Files.deleteIfExists(Paths.get(path))
    Files.deleteIfExists(Paths.get("test_output/new_image_dir"))
  }

  test("Write empty image content") {
    val path = "test_output/empty_image_file.txt"
    val exporter = new FileImageExporter(path)
    val image = new AsciiImage("".split("\n").map(_.toList).toList)
    exporter.exportImage(image)
    assert(Files.exists(Paths.get(path)))
    assert(Files.readString(Paths.get(path), StandardCharsets.UTF_8) == image.stringRepresentation)
    Files.deleteIfExists(Paths.get(path))
  }
}