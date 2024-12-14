package table

import models.table.NonLinearTransformationTable
import org.scalatest.funsuite.AnyFunSuite

class NonLinearTransformationTableTest extends AnyFunSuite {

  test("Creates a valid transformation table") {
    val asciiChars = ".:-=+*#%@"
    val table = new NonLinearTransformationTable(asciiChars).transformationTable

    assert(table.size == asciiChars.length)

    val ranges = table.keys.toSeq

    val flattened = ranges.flatMap { case (a, b) => List(a, b) }
    val minValue = flattened.min
    val maxValue = flattened.max

    assert(minValue == 0)
    assert(maxValue == 256)

    val sortedRanges = ranges.sortBy(t => (t._1, t._2))

    sortedRanges.sliding(2).foreach { case Seq((_, end1), (start2, _)) =>
      assert(end1 == start2, s"Ranges are not contiguous: ($end1, $start2)")
    }

    val sortedMap = table.toList.sortBy(_._1)
    sortedMap.map(_._2).zip(asciiChars).foreach { case (charFromTable, expectedChar) =>
      assert(charFromTable == expectedChar, s"Character mismatch: $charFromTable != $expectedChar")
    }
  }

  test("Produces non-linear ranges") {
    val asciiChars = ".:-=+*#%@"
    val table = new NonLinearTransformationTable(asciiChars).transformationTable

    val ranges = table.keys.toSeq

    val rangeWidths = ranges.map { case (start, end) => end - start }
    assert(rangeWidths.sliding(2).exists { case Seq(w1, w2) => w1 != w2 }, "Ranges appear to be linear")
  }
}
