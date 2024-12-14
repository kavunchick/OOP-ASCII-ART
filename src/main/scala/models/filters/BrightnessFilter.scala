package models.filters

import models.image.{GreyScaleImage, Image}

class BrightnessFilter(brightnessValue: Int) extends Filter {

  override def apply(image: GreyScaleImage): Unit = {
    if (brightnessValue == 0) return
    image.greyScaleImage = image.imageRepresentation.map(row =>
      row.map(pixel => {
        val newPixel = pixel + brightnessValue
        if (newPixel < 0) 0
        else if (newPixel > 255) 255
        else newPixel
      })
    )
  }
}
