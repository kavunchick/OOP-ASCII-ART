package models.filters

import models.image.GreyScaleImage

object IdentityFilter extends Filter {
  override def apply(image: GreyScaleImage): Unit = ()
}
