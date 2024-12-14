package converters

import models.converters.ToAsciiConverter
import org.scalatest.funsuite.AnyFunSuite
import models.image.GreyScaleImage
class ToAsciiConverterTest extends AnyFunSuite {

  test("Greyscale to ASCII conversion single line") {
    val converter = new ToAsciiConverter()
    val greyscaleImage = GreyScaleImage(List(List(0, 128, 255)))
    val asciiImage = converter.convert(greyscaleImage)

    assert(asciiImage.imageRepresentation.length == 1)
    assert(asciiImage.imageRepresentation.head.length == 3)
    assert(asciiImage.imageRepresentation.head(0) == '$')
    assert(asciiImage.imageRepresentation.head(1) == 'x')
    assert(asciiImage.imageRepresentation.head(2) == ' ')
  }

  test("Empty greyscale") {
    val converter = new ToAsciiConverter()
    val greyscaleImage = GreyScaleImage(List.empty[List[Int]])
    val asciiImage = converter.convert(greyscaleImage)

    assert(asciiImage.imageRepresentation.isEmpty)
  }

  test("Greyscale to ASCII conversion multiple lines") {
    val image = new GreyScaleImage(List(List(0, 128, 255), List(0, 128, 255)))
    val converter = new ToAsciiConverter()
    val ascii = converter.convert(image)
    assert(ascii.stringRepresentation == "$x \n$x ")
  }

}