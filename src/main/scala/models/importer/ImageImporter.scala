package models.importer

import models.image.RgbImage

/**
 * Trait for importing images
 */
trait ImageImporter {

  /**
   * Imports an image from a given path
   * @param path the path of the image
   * @return [[RgbImage]] the imported image in RGB format
   */
  def importImage(path: String) : RgbImage
}
