package elevatorsystem

class Elevator(
              val id: Int = 1,
              var location: Int = 0,
              var destination: Int = 0
              ) {

  val destinationManager: DestinationManager
    = {
      val diff: Int = destination - location
      val currentDirection: Int = if(diff == 0) 0 else if(diff > 0) 1 else -1
      new DestinationManager(currentDirection)
    }
  val elevatorEngine: Engine
    = new Engine(this)

  def step(): Unit = {
    elevatorEngine.move
  }

  def call(floor: Int, direction: Int): Unit = {
    if(floor != location)
      destinationManager.add(floor, direction)
  }

  def update(newLocation: Int, newDestination: Int): Unit = {
    if(location != destination)
      destinationManager.forceHead(destination)
    location = newLocation
    destination = newDestination
  }
}
