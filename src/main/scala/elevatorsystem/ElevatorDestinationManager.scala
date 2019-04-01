package elevatorsystem

import scala.collection.mutable.Queue

class ElevatorDestinationManager extends DestinationManager {

  var destinations: scala.collection.mutable.Queue[Int] = scala.collection.mutable.Queue.empty[Int]
  var lastDirection: Int = 0

  override def next(): Int = {
    destinations.dequeue
  }

  override def hasNext(): Boolean = {
    destinations.nonEmpty
  }

  override def add(location: Int, direction: Int): Unit = {
    destinations += location
    lastDirection = direction
  }

  override def forceHead(location: Int): Unit = {
    destinations = scala.collection.mutable.Queue(location) ++ destinations
  }
}
