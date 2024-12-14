import UI.inputParser.ConsoleParser
import models.converters.{ToAsciiConverter, ToGreyScaleConverter}
import models.filters.MixedFilter
import models.importer.{ImageImporter, RandomImageImporter, StandardImageImporter}
import models.table.{DefaultLinearTransformationTable, DefaultNonLinearTransformationTable, LinearTransformationTable}

class AsciiArtApp {
  private val toGreyScaleConvertor = ToGreyScaleConverter()
  private val toAsciiConverter = ToAsciiConverter()
  private var imageImporter = Option.empty[ImageImporter]
  var outputs = List.empty[String]

  def run(args: Array[String]): Unit = {
    val inputHandler = new ConsoleParser(args)
    inputHandler.parseInput()
    if (inputHandler.inputInfo.imagePath == "random") imageImporter = Some(new RandomImageImporter())
    else imageImporter = Some(new StandardImageImporter())
    if (inputHandler.inputInfo.asciiTable != "") {
      if (inputHandler.inputInfo.asciiTable == "nonlinear")
        toAsciiConverter.table = DefaultNonLinearTransformationTable
      else if (inputHandler.inputInfo.asciiTable == "linear")
        toAsciiConverter.table = DefaultLinearTransformationTable
      else
        toAsciiConverter.table = new LinearTransformationTable(inputHandler.inputInfo.asciiTable)
    }
    val rgbImage = imageImporter.get.importImage(inputHandler.inputInfo.imagePath)
    val greyScaleImage = toGreyScaleConvertor.convert(rgbImage)
    for (filter <- inputHandler.inputInfo.filters) {
      val mixedFilter = new MixedFilter(filter._2)
      mixedFilter.apply(greyScaleImage)
      val image = toAsciiConverter.convert(greyScaleImage)
      outputs = outputs :+ image.stringRepresentation
      filter._1._1.exportImage(image)
    }
  }
}
