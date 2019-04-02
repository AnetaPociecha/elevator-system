package elevatorsystem

class DestinationManager(
                          var lastDirection: Int = 0
                        ) {

  var destinations: scala.collection.mutable.Queue[Int] = scala.collection.mutable.Queue.empty[Int]

  def next(): Int = {
    destinations.dequeue
  }

  def add(location: Int, direction: Int): Unit = {
    destinations += location
    lastDirection = direction
  }

  def forceHead(location: Int): Unit = {
    destinations = scala.collection.mutable.Queue(location) ++ destinations
  }
}
