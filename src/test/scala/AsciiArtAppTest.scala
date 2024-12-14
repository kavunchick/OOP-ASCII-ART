import org.scalatest.funsuite.AnyFunSuite
import java.io.File

class AsciiArtAppTest extends AnyFunSuite {

  test("Valid image path and filters") {
    val args =
      Array("--image", "src/test/resources/test_image.png", "--output-console")
    val app = new AsciiArtApp
    app.run(args)
    assert(app.outputs.nonEmpty)
    assert(app.outputs.head == "|/fj\n/jnv\ntnzJ\nfuUm")
  }

  test("Multiple filters and outputs") {
    val args = Array(
      "--image",
      "src/test/resources/test_image.png",
      "--rotate",
      "+90",
      "--output-console",
      "--invert",
      "--output-file",
      "src/test/resources/output.txt")
    val app = new AsciiArtApp
    app.run(args)
    assert(app.outputs.size == 2)
    assert(app.outputs.head == "ft/|\nunj/\nUznf\nmJvj")
    assert(app.outputs(1) == "ccXU\nrxvX\n|txc\n?(jv")
    val source = scala.io.Source.fromFile("src/test/resources/output.txt")
    val lines = try source.mkString
    finally source.close()
    assert(lines == "ccXU\nrxvX\n|txc\n?(jv")
    val file = new File("src/test/resources/output.txt")
    file.delete()
  }

  test("Invalid image path") {
    val args = Array("--image", "invalid_path.png", "--output-console")
    val app = new AsciiArtApp
    assertThrows[IllegalArgumentException] {
      app.run(args)
    }
  }

  test("Invalid filter") {
    val args = Array(
      "--image",
      "src/test/resources/test_image.png",
      "--rotate",
      "invalid",
      "--output-console")
    val app = new AsciiArtApp
    assertThrows[IllegalArgumentException] {
      app.run(args)
    }
  }

  test("Invalid output") {
    val args =
      Array("--image", "src/test/resources/test_image.png", "--output-invalid")
    val app = new AsciiArtApp
    assertThrows[IllegalArgumentException] {
      app.run(args)
    }
  }

  test("Not default table") {
    val args = Array(
      "--image",
      "src/test/resources/test_image.png",
      "--custom-table",
      "bourke-small",
      "--output-console")
    val app = new AsciiArtApp
    app.run(args)
    assert(app.outputs.nonEmpty)
    assert(app.outputs.head == "s---\n--ee\n-eek\n-ekr")
  }

  test("NonLinear table") {
    val args = Array(
      "--image",
      "src/test/resources/test_image.png",
      "--table", "nonlinear",
      "--output-console")
    val app = new AsciiArtApp
    app.run(args)
    assert(app.outputs.nonEmpty)
    assert(app.outputs.head == "##**\n#***\n***+\n**+=")
  }

  test("Linear table") {
    val args = Array(
      "--image",
      "src/test/resources/test_image.png",
      "--table", "linear",
      "--output-console")
    val app = new AsciiArtApp
    app.run(args)
    assert(app.outputs.nonEmpty)
    assert(app.outputs.head == "|/fj\n/jnv\ntnzJ\nfuUm")
  }

  test("Custom table") {
    val args = Array(
      "--image",
      "src/test/resources/test_image.png",
      "--custom-table", "custom:)table",
      "--output-console")
    val app = new AsciiArtApp
    app.run(args)
    assert(app.outputs.nonEmpty)
    assert(app.outputs.head == "))):\n)::m\n):mo\n):mt")
  }
}
