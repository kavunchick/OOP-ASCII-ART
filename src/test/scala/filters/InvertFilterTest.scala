package filters

import models.filters.InvertFilter
import models.image.GreyScaleImage
import org.scalatest.funsuite.AnyFunSuite

class InvertFilterTest extends AnyFunSuite {

  test("Invert 2x2 image") {
    val image = new GreyScaleImage(List(
      List(0, 255),
      List(128, 64)
      ))
    val filter = new InvertFilter()
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(255, 0),
      List(127, 191)
    ))
  }

  test("Invert 3x3 image") {
    val image = new GreyScaleImage(List(
      List(0, 128, 255),
      List(64, 192, 32),
      List(16, 240, 8)
    ))
    val filter = new InvertFilter()
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(255, 127, 0),
      List(191, 63, 223),
      List(239, 15, 247)
    ))
  }

  test("Invert greyscale(0)->greyscale(255)") {
    val image = new GreyScaleImage(List(
      List(0, 0),
      List(0, 0)
    ))
    val filter = new InvertFilter()
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(255, 255),
      List(255, 255)
    ))
  }

  test("Invert greyscale(255)->greyscale(0)") {
    val image = new GreyScaleImage(List(
      List(255, 255),
      List(255, 255)
    ))
    val filter = new InvertFilter()
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(0, 0),
      List(0, 0)
    ))
  }

  test("Invert an empty image") {
    val image = new GreyScaleImage(List())
    val filter = new InvertFilter()
    filter.apply(image)
    assert(image.greyScaleImage == List())
  }
}