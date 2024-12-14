package UI.inputParser

import models.exporter.ImageExporter
import models.filters.Filter

/**
 * InputInfo class is responsible for storing the input information
 *
 * @param args array of strings which represent the input
 */
case class InputInfo(args: Array[String]) {
    var filtersAsString: Map[(String, Int), List[String]] = Map()
    var filters: Map[(ImageExporter, Int), Seq[Filter]] = Map()
    var imagePath: String = ""
    var imageFormat: String = ""
    var asciiTable: String = ""
    var params: Array[String] = args
}
