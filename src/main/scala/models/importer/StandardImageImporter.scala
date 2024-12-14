package models.importer

import models.image.RgbImage

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class StandardImageImporter extends ImageImporter {

  override def importImage(path: String): RgbImage = {
    try {
      val image: BufferedImage = ImageIO.read(new File(path))
      if (image == null)
        throw new IllegalArgumentException("Image not found")
      var colorImage: List[List[Color]] = List()
      for (y <- 0 until image.getHeight) {
        var row: List[Color] = List()
        for (x <- 0 until image.getWidth) {
          val color = new Color(image.getRGB(x, y))
          row = row :+ color
        }
        colorImage = colorImage :+ row
      }
      RgbImage(colorImage)
  } catch
      case e: Exception => throw new IllegalArgumentException("Image not found")
  }
}
