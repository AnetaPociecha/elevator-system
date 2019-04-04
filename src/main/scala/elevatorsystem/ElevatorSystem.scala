package elevatorsystem

class ElevatorSystem(
                    var elevators: List[Elevator]
                    ) extends System {

  var controller: Controller = new Controller(elevators)
  var validator: Validator = new Validator(elevators)

  override def pickup(location: Int, direction: Int): Unit = {
    validator.checkFloor(location)
    validator.checkDirection(direction)

    if( validator.checkPickup(location, direction) )
      controller.assign(location, direction)
  }

  override def update(id: Int, location: Int, destination: Int): Unit = {
    validator.checkFloor(location)
    validator.checkFloor(destination)

    val elevator: Elevator = getElevatorById(id)
    elevator.update(location, destination)
  }

  override def step(): Unit = {
    for(elevator <- elevators)
      elevator.step()
  }

  override def status(): List[(Int, Int, Int)] = {
    var status: List[(Int, Int, Int)] = List()
    for(elevator <- elevators)
      status = (elevator.id, elevator.location, elevator.destination) :: status
    status
  }

  @throws(classOf[Exception])
  def getElevatorById(id: Int): Elevator = {
    val elevator: Option[Elevator] = elevators.toStream.find(e => e.id == id)
    validator.checkElevator(elevator, id)
    elevator.get
  }
}
