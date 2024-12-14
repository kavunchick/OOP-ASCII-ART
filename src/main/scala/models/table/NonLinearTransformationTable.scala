package models.table

class NonLinearTransformationTable(val asciiChars: String)
    extends TransformationTable {
  val transformationTable: Map[(Int, Int), Char] = makeTable()

  override def makeTable(): Map[(Int, Int), Char] = {
    val maxGrayScale = 256
    val charCount = asciiChars.length

    /**
      * Non-linear scaling function uses an exponent base of 1.2
      * @param index index of the character in the asciiChars
      * @return the scaled value
      */
    def nonLinearScale(index: Int): Int = {
      val exponentBase = 1.2
      (Math.pow(index.toDouble / (charCount - 1), exponentBase) * maxGrayScale).toInt
    }

    (for {
      i <- 0 until charCount
    } yield {
      val start = if (i == 0) 0 else nonLinearScale(i - 1)
      val end = if (i < charCount - 1) nonLinearScale(i) else maxGrayScale
      (start, end) -> asciiChars(i)
    }).toMap
  }
}
