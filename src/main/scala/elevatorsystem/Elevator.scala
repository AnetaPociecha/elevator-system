package elevatorsystem

class Elevator(
              val id: Int = 1,
              var location: Int = 0
              ) {

  var destination: Int = location

  val destinationManager: DestinationManager = new DestinationManager()

  var engine: Engine = new Engine(this)

  def step(): Unit = {
    engine.move()
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
