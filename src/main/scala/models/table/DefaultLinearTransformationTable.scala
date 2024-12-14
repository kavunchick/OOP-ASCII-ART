package models.table

object DefaultLinearTransformationTable extends TransformationTable {
  val asciiChars =
    "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "
  val transformationTable: Map[(Int, Int), Char] =
    new LinearTransformationTable(asciiChars).makeTable()
  def makeTable(): Map[(Int, Int), Char] =
    LinearTransformationTable(asciiChars).makeTable()
}
