package models.filters

import models.image.GreyScaleImage

class RotateFilter(degree: Int) extends Filter {

  private def rotate(image: GreyScaleImage): Unit =
    image.greyScaleImage = image.imageRepresentation.transpose.map(_.reverse)

  override def apply(image: GreyScaleImage): Unit = {
    if (degree % 90 != 0)
      throw new IllegalArgumentException(
        "Rotation must be a multiple of 90 degrees")
    val coefficient = degree % 360 / 90
    val numberOfRotations =
      if (coefficient < 0) coefficient + 4 else coefficient
    for (i <- 1 to numberOfRotations) rotate(image)
  }
}
