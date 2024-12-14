package filters

import models.filters.BrightnessFilter
import models.image.GreyScaleImage
import org.scalatest.funsuite.AnyFunSuite

class BrightnessFilterTest extends AnyFunSuite {

  test("Brightness +10") {
    val image = new GreyScaleImage(List(
      List(10, 20),
      List(30, 40)
    ))
    val filter = new BrightnessFilter(10)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(20, 30),
      List(40, 50)
    ))
  }

  test("Brightness -10") {
    val image = new GreyScaleImage(List(
      List(10, 20),
      List(30, 40)
    ))
    val filter = new BrightnessFilter(-10)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(0, 10),
      List(20, 30)
    ))
  }

  test("Brightness +0") {
    val image = new GreyScaleImage(List(
      List(10, 20),
      List(30, 40)
    ))
    val filter = new BrightnessFilter(0)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(10, 20),
      List(30, 40)
    ))
  }

  test("Brightness +20") {
    val image = new GreyScaleImage(List(
      List(250, 240),
      List(230, 220)
    ))
    val filter = new BrightnessFilter(20)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(255, 255),
      List(250, 240)
    ))
  }

  test("Brightness -50") {
    val image = new GreyScaleImage(List(
      List(10, 20),
      List(30, 40)
    ))
    val filter = new BrightnessFilter(-50)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(0, 0),
      List(0, 0)
    ))
  }

  test("Brightness empty image") {
    val image = new GreyScaleImage(List())
    val filter = new BrightnessFilter(10)
    filter.apply(image)
    assert(image.greyScaleImage == List())
  }
}