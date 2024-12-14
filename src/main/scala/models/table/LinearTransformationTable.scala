package models.table

class LinearTransformationTable(val asciiChars: String)
    extends TransformationTable {
  val transformationTable: Map[(Int, Int), Char] = makeTable()

  override def makeTable(): Map[(Int, Int), Char] = {
    val scaleFactor = 256 / asciiChars.length.toDouble
    (for {
      i <- 0 until asciiChars.length
    } yield {
      val index = (i * scaleFactor).toInt
      (index, (index + scaleFactor).toInt) -> asciiChars(i)
    }).toMap
  }
}
