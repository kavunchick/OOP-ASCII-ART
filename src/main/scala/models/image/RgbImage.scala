package models.image

import java.awt.Color

class RgbImage(var rgbImage: List[List[Color]]) extends Image[Color] {
  override def imageRepresentation: List[List[Color]] = rgbImage
}
