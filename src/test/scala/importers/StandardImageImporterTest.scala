package importers

import models.image.RgbImage
import models.importer.StandardImageImporter
import org.scalatest.funsuite.AnyFunSuite

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import java.awt.Color

class StandardImageImporterTest extends AnyFunSuite{

  /**
   * Creates a temporary image file with 2x2 pixels.
   * @return The temporary image file.
   */
  def createTemporaryImage(): File = {
    val image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB)
    image.setRGB(0, 0, Color.RED.getRGB)
    image.setRGB(1, 0, Color.GREEN.getRGB)
    image.setRGB(0, 1, Color.BLUE.getRGB)
    image.setRGB(1, 1, Color.YELLOW.getRGB)

    val tempFile = File.createTempFile("test-image", ".png")
    ImageIO.write(image, "PNG", tempFile)
    tempFile
  }

  test("Load a valid image") {
    val importer = new StandardImageImporter()
    val tempFile = createTemporaryImage()
    try {
      val rgbImage = importer.importImage(tempFile.getAbsolutePath)
      assert(rgbImage != null)
      assert(rgbImage.imageRepresentation.length == 2)
      assert(rgbImage.imageRepresentation.head.length == 2)
    } finally {
      tempFile.delete()
    }
  }

  test("Non-existing file") {
    val importer = new StandardImageImporter()
    val invalidPath = "non_existent_file.png"
    intercept[IllegalArgumentException] {
      importer.importImage(invalidPath)
    }
  }

  test("Null file") {
    val importer = new StandardImageImporter()
    intercept[IllegalArgumentException] {
      importer.importImage(null)
    }
  }
}
