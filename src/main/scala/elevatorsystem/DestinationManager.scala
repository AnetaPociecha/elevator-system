package elevatorsystem

import scala.collection.mutable

class DestinationManager(
                        ) {

  var destinations: mutable.Queue[(Int, Int)]
    = mutable.Queue.empty[(Int,Int)]

  def next(): Int = {
    destinations.dequeue._1
  }

  def add(location: Int, direction: Int): Unit = {
    val item: (Int, Int) = (location, direction)
    destinations += item
  }

  def forceHead(location: Int, direction: Int): Unit = {
    val item: (Int, Int) = (location, direction)
    destinations = mutable.Queue(item) ++ destinations
  }
}
