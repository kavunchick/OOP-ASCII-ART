package models.converters

import models.image.{GreyScaleImage, RgbImage}

import java.awt.Color

class ToGreyScaleConverter extends Converter[RgbImage, GreyScaleImage] {

  private def calculateGreyscaleValue(color: Color): Int =
    ((0.3 * color.getRed) + (0.59 * color.getGreen) + (0.11 * color.getBlue)).toInt

  override def convert(image: RgbImage): GreyScaleImage = {
    val greyScale = image.imageRepresentation.map { row =>
      row.map(calculateGreyscaleValue)
    }
    GreyScaleImage(greyScale)
  }

}
