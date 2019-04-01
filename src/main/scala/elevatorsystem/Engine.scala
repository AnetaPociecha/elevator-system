package elevatorsystem

class Engine(
              var elevator: Elevator
            ) {

  def move(): Unit = {
    if(elevator.location == elevator.destination) {
      handleStop()
    } else {
      handleSimpleMove()
    }
  }

  def handleSimpleMove(): Unit = {
    if(elevator.location < elevator.destination) elevator.location += 1
    else elevator.location -= 1
  }

  def handleStop(): Unit = {
    if(elevator.destinationManager.destinations.nonEmpty) {
      elevator.destination = elevator.destinationManager.next()
    }
  }
}
