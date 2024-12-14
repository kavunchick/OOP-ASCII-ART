package UI.inputParser.handlers
import UI.inputParser.InputInfo

/**
 * BasicInfoHandler class is responsible for handling the basic information (image path and ascii table)
 */
class BasicInfoHandler extends Handler {

  override def process(info: InputInfo): Unit = {
    var imageWasFound = false
    var deleteNext = false
    info.params = info.params.filter {
      case "--image-random" =>
        if (imageWasFound)
          throw new IllegalArgumentException("Only one image is allowed.")
        info.imagePath = "random"
        imageWasFound = true
        false
      case "--image" if info.params.indexOf("--image") + 1 < info.params.length && !info.params(info.params.indexOf("--image") + 1).startsWith("--") =>
        info.imagePath = info.params(info.params.indexOf("--image") + 1).replace("\"", "")
        val imageFormat = info.imagePath.split("\\.").last.trim.replace("\"", "")
        deleteNext = true
        if (imageWasFound)
          throw new IllegalArgumentException("Only one image is allowed.")
        if (!List("png", "jpg").contains(imageFormat))
          throw new IllegalArgumentException("Unsupported image format.")
        imageWasFound = true
        false
      case "--table" if info.params.indexOf("--table") + 1 < info.params.length && !info.params(info.params.indexOf("--table") + 1).startsWith("--") =>
        val table = info.params(info.params.indexOf("--table") + 1).replace("\"", "")
        if(table != "nonlinear" && table != "linear")
          throw new IllegalArgumentException("Unsupported table type.")
        info.asciiTable = table
        deleteNext = true
        false
      case "--custom-table" if info.params.indexOf("--custom-table") + 1 < info.params.length && !info.params(info.params.indexOf("--custom-table") + 1).startsWith("--") =>
        info.asciiTable = info.params(info.params.indexOf("--custom-table") + 1).stripPrefix("\"").stripSuffix("\"")
        deleteNext = true
        false
      case _ =>
        if (deleteNext) {
          deleteNext = false
          false
        } else true
    }
  }
}