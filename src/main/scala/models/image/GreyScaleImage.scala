package models.image

class GreyScaleImage(var greyScaleImage: List[List[Int]]) extends Image[Int] {
  override def imageRepresentation: List[List[Int]] = greyScaleImage
}
