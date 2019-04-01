package elevatorsystem

class Controller(
                elevators: List[Elevator]
                ) {

  def assign(location: Int, direction: Int): Unit = {
    val elevator = pickElevator(location,direction)
    elevator.call(location)
  }

  def pickElevator(location: Int, direction: Int): Elevator = {
    elevators(0)
  }

}
