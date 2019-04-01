package elevatorsystem

trait DestinationManager {
  def next(): Int
  def hasNext(): Boolean
  def add(location: Int): Unit
  def forceHead(location: Int): Unit
}
