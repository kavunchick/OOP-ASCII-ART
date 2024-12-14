package models.image

class AsciiImage(var asciiImage: List[List[Char]]) extends Image[Char] {
  override def imageRepresentation: List[List[Char]] = asciiImage
  val stringRepresentation: String = asciiImage.map(_.mkString).mkString("\n")
}
