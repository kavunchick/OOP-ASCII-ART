package table

import models.table.LinearTransformationTable
import org.scalatest.funsuite.AnyFunSuite

class LinearTransformationTableTest extends AnyFunSuite {

  test("Non-empty transformation table") {
    val table = new LinearTransformationTable(" .:-=+*#%@").makeTable()
    assert(table.nonEmpty)
  }

  test("Table with correct size") {
    val asciiChars = " .:-=+*#%@"
    val table = new LinearTransformationTable(asciiChars).makeTable()
    assert(table.size == asciiChars.length)
  }

  test("Table with valid ASCII characters") {
    val asciiChars = " .:-=+*#%@"
    val table = new LinearTransformationTable(asciiChars).makeTable()
    assert(table.values.forall(asciiChars.contains))
  }

  test("Consistent results") {
    val asciiChars = " .:-=+*#%@"
    val table1 = new LinearTransformationTable(asciiChars).makeTable()
    val table2 = new LinearTransformationTable(asciiChars).makeTable()
    assert(table1 == table2)
  }

  test("Empty ASCII characters string") {
    val table = new LinearTransformationTable("").makeTable()
    assert(table.isEmpty)
  }

  test("Single ASCII character") {
    val table = new LinearTransformationTable("@").makeTable()
    assert(table.size == 1)
    assert(table.values.head == '@')
  }

  test("Keys represent linearity") {
    val asciiChars = " .:-=+*#%@"
    val table = new LinearTransformationTable(asciiChars).makeTable()
    val keys = table.keys.toList.sortBy(_._1)
    val scaleFactor = 256 / asciiChars.length.toDouble

    for (i <- keys.indices) {
      val expectedStart = (i * scaleFactor).toInt
      val expectedEnd = (expectedStart + scaleFactor).toInt
      assert(keys(i) == (expectedStart, expectedEnd))
    }
  }
}