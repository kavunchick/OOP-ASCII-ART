package models.converters

import models.image.Image
import models.table.{DefaultLinearTransformationTable, TransformationTable}

/**
 * A trait that defines a converter from one image type to another.
 *
 * @tparam T The type of the input image.
 * @tparam U The type of the output image.
 */
trait Converter[T <: Image[?], U <: Image[?]] {
  var table: TransformationTable = DefaultLinearTransformationTable

  def convert(image: T): U
}
