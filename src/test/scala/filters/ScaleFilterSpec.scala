package filters

import models.filters.ScaleFilter
import models.image.GreyScaleImage
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ScaleFilterSpec extends AnyFunSuite with Matchers {

  test("Scale 1") {
    val image = new GreyScaleImage(List(List(1, 2), List(3, 4)))
    val filter = new ScaleFilter(1)
    filter.apply(image)
    image.greyScaleImage shouldEqual List(List(1, 2), List(3, 4))
  }

  test("Scale 0.25") {
    val image = new GreyScaleImage(List(
      List(1, 2, 3, 4),
      List(5, 6, 7, 8),
      List(9, 10, 11, 12),
      List(13, 14, 15, 16)
    ))
    val filter = new ScaleFilter(0.25)
    filter.apply(image)
    image.greyScaleImage shouldEqual List(
      List(1, 3),
      List(9, 11)
    )
  }

  test("Scale 4") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val filter = new ScaleFilter(4)
    filter.apply(image)
    image.greyScaleImage shouldEqual List(
      List(1, 1, 2, 2),
      List(1, 1, 2, 2),
      List(3, 3, 4, 4),
      List(3, 3, 4, 4)
    )
  }

  test("Scale invalid ratio 2") {
    val image = new GreyScaleImage(List(List(1, 2), List(3, 4)))
    val filter = new ScaleFilter(2)
    an[IllegalArgumentException] should be thrownBy filter.apply(image)
  }

  test("Scale invalid ratio 0 && -1") {
    val image = new GreyScaleImage(List(List(1, 2), List(3, 4)))
    val filter = new ScaleFilter(0)
    an[IllegalArgumentException] should be thrownBy filter.apply(image)

    val negativeFilter = new ScaleFilter(-1)
    an[IllegalArgumentException] should be thrownBy negativeFilter.apply(image)
  }

  test("Scale invalid ratio 0.5 && 3") {
    val image = new GreyScaleImage(List(List(1, 2), List(3, 4)))

    val invalidFilter1 = new ScaleFilter(0.5)
    an[IllegalArgumentException] should be thrownBy invalidFilter1.apply(image)

    val invalidFilter2 = new ScaleFilter(3)
    an[IllegalArgumentException] should be thrownBy invalidFilter2.apply(image)
  }
}
