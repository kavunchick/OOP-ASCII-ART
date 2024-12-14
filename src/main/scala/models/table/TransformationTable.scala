package models.table

trait TransformationTable {
  def asciiChars: String
  val transformationTable: Map[(Int, Int), Char]
  def makeTable(): Map[(Int, Int), Char]
}
