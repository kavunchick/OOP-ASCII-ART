package models.exporter

import models.image.AsciiImage

class ConsoleImageExporter extends ImageExporter {

  override def exportImage(image: AsciiImage): String = {
    println(image.stringRepresentation)
    image.stringRepresentation
  }

}
