package models.filters

import models.image.GreyScaleImage

class FlipFilter(axis: Int) extends Filter {

  override def apply(image: GreyScaleImage): Unit = {
    axis match {
      case 1 =>
        image.greyScaleImage = image.imageRepresentation.reverse
      case 0 =>
        image.greyScaleImage = image.imageRepresentation.map(_.reverse)
      case _ =>
        throw new IllegalArgumentException("Invalid axis. Must be 0 or 1")
    }
  }
}
