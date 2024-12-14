package filters

import models.filters.RotateFilter
import models.image.GreyScaleImage
import org.scalatest.funsuite.AnyFunSuite

class RotateFilterTest extends AnyFunSuite {

  test("Rotate +90") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val filter = new RotateFilter(90)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(3, 1),
      List(4, 2)
    ))
  }

  test("Rotate +180") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val filter = new RotateFilter(180)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(4, 3),
      List(2, 1)
    ))
  }

  test("Rotate +270") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val filter = new RotateFilter(270)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(2, 4),
      List(1, 3)
    ))
  }

  test("Rotate +360") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val filter = new RotateFilter(360)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(1, 2),
      List(3, 4)
    ))
  }

  test("Rotate invalid degrees") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val filter = new RotateFilter(45)
    assertThrows[IllegalArgumentException] {
      filter.apply(image)
    }
  }

  test("Rotate -90") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val filter = new RotateFilter(-90)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(2, 4),
      List(1, 3)
    ))
  }
}