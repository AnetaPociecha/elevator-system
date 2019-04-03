package elevatorsystem

class Elevator(
              val id: Int = 1,
              var location: Int = 0
              ) {

  var destination: Int = location

  val destinationManager: DestinationManager
    = new DestinationManager()

  var engine: Engine
    = new Engine(this)

  def step(): Unit = {
    engine.move()
  }

  def call(floor: Int, direction: Int): Unit = {
    if(floor != location)
      destinationManager.add(floor, direction)
  }

  def update(newLocation: Int, newDestination: Int): Unit = {
    if(location != destination) {
      val direction: Int = if(destination > location) 1 else -1
      destinationManager.forceHead(destination, direction)
    }

    location = newLocation
    destination = newDestination
  }
}
