package models.filters

import models.image.GreyScaleImage

trait Filter {

  /**
   * Applies a filter to an image.
   * @param image grey scale image to apply the filter to
   * @return Unit
   */
  def apply(image: GreyScaleImage): Unit
}
