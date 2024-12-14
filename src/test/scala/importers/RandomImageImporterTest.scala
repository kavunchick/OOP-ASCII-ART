package importers

import org.scalatest.funsuite.AnyFunSuite
import models.importer.RandomImageImporter

import java.awt.Color

class RandomImageImporterTest extends AnyFunSuite {

  test("Generates random color image") {
    val importer = new RandomImageImporter()
    val image = importer.importImage("")

    assert(image.imageRepresentation.nonEmpty)
    assert(image.imageRepresentation.forall(_.nonEmpty))
    assert(image.imageRepresentation.forall(_.forall(_.isInstanceOf[Color])))
  }

  test("Range of image sizes") {
    val importer = new RandomImageImporter()
    val image = importer.importImage("")

    assert(image.imageRepresentation.length >= 120 && image.imageRepresentation.length <= 800)
    assert(image.imageRepresentation.head.length >= 200 && image.imageRepresentation.head.length <= 800)
  }


  test("Different seeds generate different images") {
    val importer1 = new RandomImageImporter(Some(12345L))
    val importer2 = new RandomImageImporter(Some(54321L))
    val image1 = importer1.importImage("")
    val image2 = importer2.importImage("")

    assert(image1.imageRepresentation != image2.imageRepresentation)
  }

  test("Consistent seed generates consistent images") {
    val seed = Some(12345L)
    val importer1 = new RandomImageImporter(seed)
    val importer2 = new RandomImageImporter(seed)
    val image1 = importer1.importImage("")
    val image2 = importer2.importImage("")

    assert(image1.imageRepresentation == image2.imageRepresentation)
  }
}