package models.filters

import models.image.GreyScaleImage

class MixedFilter(filters: Seq[Filter]) extends Filter {

  override def apply(image: GreyScaleImage): Unit =
    for (filter <- filters) filter.apply(image)
}
