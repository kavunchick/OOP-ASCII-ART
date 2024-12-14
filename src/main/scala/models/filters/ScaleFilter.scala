package models.filters

import models.image.GreyScaleImage

class ScaleFilter(ratio: Float) extends Filter {

  /**
    * Scales the image up or down by a factor of 0.25
    * @param image the image to be scaled
    */
  private def scaleDown(image: GreyScaleImage, width: Int): Unit = {
    var greyScaleRows = List[List[Int]]()
    for (y <- image.imageRepresentation.indices by 2) {
      var greyScaleRow = List[Int]()
      for (x <- 0 until width by 2)
        greyScaleRow = greyScaleRow :+ image.imageRepresentation(y)(x)
      greyScaleRows = greyScaleRows :+ greyScaleRow
    }
    image.greyScaleImage = greyScaleRows
  }

  /**
    * Scales the image up by a factor of 4.
    * @param image the image to be scaled
    */
  private def scaleUp(image: GreyScaleImage, width: Int): Unit = {
    var greyScaleRows = List[List[Int]]()
    for (y <- image.imageRepresentation.indices) {
      var greyScaleRow = List[Int]()
      for (x <- 0 until width)
        greyScaleRow = greyScaleRow :+ image.imageRepresentation(y)(x) :+ image
          .imageRepresentation(y)(x)
      greyScaleRows = greyScaleRows :+ greyScaleRow :+ greyScaleRow
    }
    image.greyScaleImage = greyScaleRows
  }

  override def apply(image: GreyScaleImage): Unit = {
    val originalWidth = image.imageRepresentation.head.length
    ratio match {
      case 1    => ()
      case 0.25 => scaleDown(image, originalWidth)
      case 4    => scaleUp(image, originalWidth)
      case _ =>
        throw new IllegalArgumentException("Scale ratio must be 0.25, 1, or 4")
    }
  }
}
