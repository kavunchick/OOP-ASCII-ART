package filters

import models.filters.FlipFilter
import models.image.GreyScaleImage
import org.scalatest.funsuite.AnyFunSuite

class FlipFilterTest extends AnyFunSuite {

  test("Flip x") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val filter = new FlipFilter(1)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(3, 4),
      List(1, 2)
    ))
  }

  test("Flip y") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val filter = new FlipFilter(0)
    filter.apply(image)
    assert(image.greyScaleImage == List(
      List(2, 1),
      List(4, 3)
    ))
  }

  test("Flip invalid axis") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val filter = new FlipFilter(2)
    assertThrows[IllegalArgumentException] {
      filter.apply(image)
    }
  }

  test("Flip empty image") {
    val image = new GreyScaleImage(List())
    val filter = new FlipFilter(1)
    filter.apply(image)
    assert(image.greyScaleImage == List())
  }
}