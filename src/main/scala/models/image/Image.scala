package models.image

/**
 * Trait that represents an image.
 * @tparam T The type of the image.
 */
trait Image[T] {
  def imageRepresentation: List[List[T]]
}
