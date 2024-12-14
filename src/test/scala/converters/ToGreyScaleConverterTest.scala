package converters

import models.converters.ToGreyScaleConverter
import org.scalatest.funsuite.AnyFunSuite
import models.image.RgbImage

import java.awt.Color

class ToGreyScaleConverterTest extends AnyFunSuite {

  test("RGB values to greyscale conversion") {
    val converter = new ToGreyScaleConverter()
    val rgbImage = RgbImage(List(List(new Color(255, 0, 0), new Color(0, 255, 0), new Color(0, 0, 255))))
    val greyscaleImage = converter.convert(rgbImage)

    assert(greyscaleImage.imageRepresentation.length == 1)
    assert(greyscaleImage.imageRepresentation.head.length == 3)
    assert(greyscaleImage.imageRepresentation.head(0) == 76)
    assert(greyscaleImage.imageRepresentation.head(1) == 150)
    assert(greyscaleImage.imageRepresentation.head(2) == 28)
  }

  test("Empty RGB image") {
    val converter = new ToGreyScaleConverter()
    val rgbImage = RgbImage(List.empty[List[Color]])
    val greyscaleImage = converter.convert(rgbImage)

    assert(greyscaleImage.imageRepresentation.isEmpty)
  }

  test("RGB image with multiple rows") {
    val converter = new ToGreyScaleConverter()
    val rgbImage = RgbImage(List(
      List(new Color(255, 0, 0)),
      List(new Color(0, 255, 0), new Color(0, 0, 255)))
    )
    val greyscaleImage = converter.convert(rgbImage)

    assert(greyscaleImage.imageRepresentation.length == 2)
    assert(greyscaleImage.imageRepresentation.head.length == 1)
    assert(greyscaleImage.imageRepresentation(1).length == 2)
    assert(greyscaleImage.imageRepresentation.head(0) == 76)
    assert(greyscaleImage.imageRepresentation(1).head == 150)
    assert(greyscaleImage.imageRepresentation(1)(1) == 28)
  }

  test("RGB image edge case") {
    val converter = new ToGreyScaleConverter()
    val rgbImage = RgbImage(List(List(new Color(0, 0, 0), new Color(255, 255, 255))))
    val greyscaleImage = converter.convert(rgbImage)

    assert(greyscaleImage.imageRepresentation.length == 1)
    assert(greyscaleImage.imageRepresentation.head.length == 2)
    assert(greyscaleImage.imageRepresentation.head(0) == 0)
    assert(greyscaleImage.imageRepresentation.head(1) == 255)
  }
}
