package elevatorsystem

class Elevator(
              val id: Int = 1,
              var location: Int = 0,
              var destination: Int = 0
              ) {

  val destinationManager: ElevatorDestinationManager
    = new ElevatorDestinationManager()
  val elevatorEngine: ElevatorEngine
    = new ElevatorEngine(this)

  def step(): Unit = {
    elevatorEngine.move
  }

  def call(location: Int, direction: Int): Unit = {
    destinationManager.add(location, direction)
  }

  def update(newLocation: Int, newDestination: Int): Unit = {
    if(location != destination)
      destinationManager.forceHead(destination)
    location = newLocation
    destination = newDestination
  }

}
