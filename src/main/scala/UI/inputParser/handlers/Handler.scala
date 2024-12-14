package UI.inputParser.handlers
import UI.inputParser.InputInfo

/**
 * Handler trait is responsible for handling the input
 */
trait Handler {
  def process(info: InputInfo): Unit
}
