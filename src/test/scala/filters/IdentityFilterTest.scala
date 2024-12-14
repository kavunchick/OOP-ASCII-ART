package filters

import org.scalatest.funsuite.AnyFunSuite
import models.image.GreyScaleImage
import models.filters.IdentityFilter

class IdentityFilterTest extends AnyFunSuite {

  test("Identity") {
    val image = new GreyScaleImage(List(List(0, 128, 255)))
    IdentityFilter.apply(image)
    assert(image.greyScaleImage == List(List(0, 128, 255)))
  }

  test("Identity empty image content") {
    val image = new GreyScaleImage(List())
    IdentityFilter.apply(image)
    assert(image.greyScaleImage.isEmpty)
  }
}
