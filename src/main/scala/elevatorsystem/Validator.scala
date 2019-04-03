package elevatorsystem

import scala.collection.mutable

class Validator(
                 val elevators: List[Elevator]
               ) {

  @throws(classOf[Exception])
  def checkFloor(floor: Int): Unit = {
    if(floor < 0)
      throw new Exception("Location cannot be negative")
  }

  @throws(classOf[Exception])
  def checkDirection(direction: Int): Unit = {
    if(direction != -1 && direction != 1)
      throw new Exception("Direction must be equal -1 or 1")
  }

  @throws(classOf[Exception])
  def checkElevator(elevator: Option[Elevator], id: Int): Unit = {
    if(elevator.isEmpty)
      throw new Exception(s"Elevator with id $id does not exist")
  }

  def checkPickup(location: Int, direction: Int): Boolean = {
    val target: (Int, Int) = (location, direction)
    val destinations: List[mutable.Queue[(Int, Int)]] = elevators.map(e => e.destinationManager.destinations)
    val contains: Boolean = destinations.filter(d => d.contains(target)).isEmpty
    contains
  }
}
