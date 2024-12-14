package models.table

object DefaultNonLinearTransformationTable extends TransformationTable {
  val asciiChars = ".:-=+*#%@"
  val transformationTable: Map[(Int, Int), Char] =
    new NonLinearTransformationTable(asciiChars).makeTable()
  def makeTable(): Map[(Int, Int), Char] =
    new NonLinearTransformationTable(asciiChars).makeTable()
}
