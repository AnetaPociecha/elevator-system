package elevatorsystem

class ElevatorSystem(
                    var elevators: List[Elevator],
                    ) extends System {

  var controller: Controller = new Controller(elevators)

  override def pickup(location: Int, direction: Int): Unit = {
    checkFloor(location)
    checkDirection(direction)

    controller.assign(location, direction)
  }

  override def update(id: Int, location: Int, destination: Int): Unit = {
    checkFloor(location)
    checkFloor(destination)

    val elevator = getElevatorById(id)
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
    checkElevator(elevator, id)
    elevator.get
  }

  @throws(classOf[Exception])
  def checkFloor(floor: Int): Unit = {
    if(floor < 0)
      throw new Exception("Location cannot be negative")
  }

  @throws(classOf[Exception])
  def checkDirection(direction: Int): Unit = {
    if(direction < -1 || direction > 1)
      throw new Exception("Direction must be equal -1 or 0 or 1")
  }

  @throws(classOf[Exception])
  def checkElevator(elevator: Option[Elevator], id: Int): Unit = {
    if(elevator.isEmpty)
      throw new Exception(s"Elevator with id $id does not exist")
  }
}
