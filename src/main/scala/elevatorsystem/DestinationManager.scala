package elevatorsystem

import scala.collection.mutable

class DestinationManager(
                        ) {

  var lastDirection: Int = 0

  var destinations: mutable.Queue[(Int, Int)]
    = mutable.Queue.empty[(Int,Int)]

  def next(): Int = {
    lastDirection = destinations.head._2
    destinations.dequeue._1
  }

  def add(location: Int, direction: Int): Unit = {
    val item: (Int, Int) = (location, direction)
    destinations += item
  }

  def forceHead(location: Int): Unit = {
    val item: (Int, Int) = (location, lastDirection)
    destinations = mutable.Queue(item) ++ destinations
  }
}
