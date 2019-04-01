package elevatorsystem

class Elevator(
              val id: Int = 1,
              var location: Int = 0,
              var destination: Int = 0
              ) {

  val destinationQueue: ElevatorDestinationManager = new ElevatorDestinationManager()

  def step(): Unit = {

  }

  def call(location: Int): Unit = {
    destinationQueue.add(location)
  }

  def update(newLocation: Int, newDestination: Int): Unit = {
    if(location != destination)
      destinationQueue.forceHead(destination)
    location = newLocation
    destination = newDestination
  }

}
