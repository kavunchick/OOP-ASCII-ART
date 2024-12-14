package models.filters

import models.image.GreyScaleImage

class InvertFilter extends Filter {

  override def apply(image: GreyScaleImage): Unit = {
    var greyScaleMap = List[List[Int]]()
    for (list <- image.imageRepresentation) {
      var greyScaleRow = List[Int]()
      for (pixel <- list) {
        val invertedPixel = 255 - pixel
        greyScaleRow = greyScaleRow :+ invertedPixel
      }
      greyScaleMap = greyScaleMap :+ greyScaleRow
    }
    image.greyScaleImage = greyScaleMap
  }
}
