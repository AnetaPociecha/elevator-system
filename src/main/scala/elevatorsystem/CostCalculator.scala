package elevatorsystem

class CostCalculator(
                    val floor: Int,
                    val direction: Int,
                    val controllerMode: ControllerMode
                      = new NormalControllerMode()
                    ) {

  def evaluate(elevator: Elevator): Double = {
    (controllerMode.waitingTimeWeight * evaluateWaitingTime(elevator)
    + controllerMode.numberOfStopsWeight * evaluateNumberOfStops(elevator)
    + controllerMode.straightforwardWeight * evaluateStraightforward(elevator))
  }

  def evaluateWaitingTime(elevator: Elevator): Double = {
    val destList = elevator.destinationManager.destinations
    var tmpLocation = elevator.destination

    var time: Double = 0
    time += distance(elevator.destination, elevator.location)

    for(index <- destList.indices) {
      time += distance(tmpLocation, destList(index))
      tmpLocation = destList(index)
    }

    time += distance(tmpLocation, floor)

    time
  }

  def distance(start: Int, stop: Int): Double = {
    Math.abs(start - stop)
  }

  def evaluateNumberOfStops(elevator: Elevator): Int = {
    elevator.destinationManager.destinations.length
  }

  def evaluateStraightforward(elevator: Elevator): Double = {

    val lastDestination = if(elevator.destinationManager.destinations.nonEmpty)
      elevator.destinationManager.destinations.last else elevator.destination
    val lastDirection = elevator.destinationManager.lastDirection

    if( isStraightforward(lastDestination, lastDirection) )
      controllerMode.straightforwardCost
    else
      controllerMode.notStraightforwardCost
  }

  def isStraightforward(lastDestination: Int, lastDirection: Int): Boolean = {
    val directionsEqual: Boolean = direction == lastDirection
    val floorDifference: Int = lastDestination - floor

    if( ( floorDifference > 0 && directionsEqual && direction == 1 )
      || ( floorDifference < 0 && directionsEqual && direction == -1 )
      || lastDestination == floor ) true else false
  }

}
