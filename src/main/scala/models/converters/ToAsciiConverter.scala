package models.converters

import models.image.{AsciiImage, GreyScaleImage}

class ToAsciiConverter extends Converter[GreyScaleImage, AsciiImage] {

  /**
   * Maps a greyscale value to an ASCII character.
   *
   * @param grey The greyscale value to map.
   * @return The ASCII character corresponding to the given greyscale value.
   */
  private def mapToAscii(grey: Int): Char =
    table.transformationTable
      .find(entry => grey >= entry._1._1 && grey <= entry._1._2)
      .map(entry => entry._2)
      .getOrElse(' ')

  override def convert(image: GreyScaleImage): AsciiImage =
    AsciiImage(image.greyScaleImage.map { row =>
      row.map(mapToAscii)
    })

}
