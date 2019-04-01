package elevatorsystem

import scala.collection.mutable.Queue

class ElevatorDestinationManager extends DestinationManager {

  var destinations: scala.collection.mutable.Queue[Int] = scala.collection.mutable.Queue.empty[Int]

  override def next(): Int = {
    destinations.dequeue
  }

  override def hasNext(): Boolean = {
    destinations.nonEmpty
  }

  override def add(location: Int): Unit = {
    destinations += location
  }

  override def forceHead(location: Int): Unit = {
    destinations = scala.collection.mutable.Queue(location) ++ destinations
  }
}
