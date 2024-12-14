package models.importer

import models.image.RgbImage
import java.awt.Color

class RandomImageImporter(seed: Option[Long] = None) extends ImageImporter {

  private val random = seed match {
    case Some(s) => new scala.util.Random(s)
    case None    => new scala.util.Random
  }

  override def importImage(path: String): RgbImage = {
    val baseWidth = 50 + random.nextInt(151)
    val baseHeight = 30 + random.nextInt(baseWidth - 30)
    val width = baseWidth * 4
    val height = baseHeight * 4

    val image: List[List[Color]] = (0 until height).map { _ =>
      (0 until width).map { _ =>
        new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))
      }.toList
    }.toList

    RgbImage(image)
  }
}
