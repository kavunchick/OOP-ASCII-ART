package filters

import models.filters.{Filter, MixedFilter}
import models.image.GreyScaleImage
import org.scalatest.funsuite.AnyFunSuite

class MixedFilterTest extends AnyFunSuite {

  test("Mixed filter x2 filters") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val filter1 = new Filter {
      override def apply(image: GreyScaleImage): Unit = {
        image.greyScaleImage = image.greyScaleImage.map(_.map(_ + 1))
      }
    }
    val filter2 = new Filter {
      override def apply(image: GreyScaleImage): Unit = {
        image.greyScaleImage = image.greyScaleImage.map(_.map(_ * 2))
      }
    }
    val mixedFilter = new MixedFilter(Seq(filter1, filter2))
    mixedFilter.apply(image)
    assert(image.greyScaleImage == List(
      List(4, 6),
      List(8, 10)
    ))
  }

  test("No filters") {
    val image = new GreyScaleImage(List(
      List(1, 2),
      List(3, 4)
    ))
    val mixedFilter = new MixedFilter(Seq())
    mixedFilter.apply(image)
    assert(image.greyScaleImage == List(
      List(1, 2),
      List(3, 4)
    ))
  }

  test("An empty image") {
    val image = new GreyScaleImage(List())
    val filter = new Filter {
      override def apply(image: GreyScaleImage): Unit = {
        image.greyScaleImage = image.greyScaleImage.map(_.map(_ + 1))
      }
    }
    val mixedFilter = new MixedFilter(Seq(filter))
    mixedFilter.apply(image)
    assert(image.greyScaleImage == List())
  }
}