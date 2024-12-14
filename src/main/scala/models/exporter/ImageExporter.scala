package models.exporter

import models.image.AsciiImage

/**
 * An interface for exporting images.
 */
trait ImageExporter {

  def exportImage(image: AsciiImage): String

}
