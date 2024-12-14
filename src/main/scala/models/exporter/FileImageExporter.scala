package models.exporter

import models.image.AsciiImage

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths, StandardOpenOption}

class FileImageExporter(val path: String) extends ImageExporter {

  override def exportImage(image: AsciiImage): String = {
    val pathToFile = Paths.get(path)
    val parentDir = pathToFile.getParent
    if (parentDir != null && !Files.exists(parentDir))
      Files.createDirectories(parentDir)
    if (!Files.exists(pathToFile))
      Files.createFile(pathToFile)
    val writer = Files.write(
      pathToFile,
      image.stringRepresentation.getBytes(StandardCharsets.UTF_8),
      StandardOpenOption.CREATE,
      StandardOpenOption.WRITE)
    image.stringRepresentation
  }
}
