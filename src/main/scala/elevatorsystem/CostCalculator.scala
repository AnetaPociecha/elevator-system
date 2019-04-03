package elevatorsystem

class CostCalculator(
                    val floor: Int,
                    val direction: Int,
                    val controllerMode: ControllerMode
                      = new NormalControllerMode()
                    ) {

  def evaluate(elevator: Elevator): Double = {
    ( controllerMode.waitingTimeWeight * evaluateWaitingTime(elevator)
    + controllerMode.numberOfStopsWeight * evaluateNumberOfStops(elevator)
    + controllerMode.straightforwardWeight * evaluateStraightforward(elevator) )
  }

  def evaluateWaitingTime(elevator: Elevator): Int = {
    var time: Int = 0
    time += distance(elevator.destination, elevator.location)

    var tmpLocation = elevator.destination

    for(destination <- elevator.destinationManager.destinations) {
      time += distance(tmpLocation, destination._1)
      tmpLocation = destination._1
    }
    time += distance(tmpLocation, floor)

    time
  }

  def distance(start: Int, stop: Int): Int = {
    Math.abs(start - stop)
  }

  def evaluateNumberOfStops(elevator: Elevator): Int = {
    elevator.destinationManager.destinations.length
  }

  def evaluateStraightforward(elevator: Elevator): Double = {
    val lastDestination: Int = if(elevator.destinationManager.destinations.isEmpty) elevator.destination
      else elevator.destinationManager.destinations.last._1
    val lastDirection: Int = if(elevator.destinationManager.destinations.isEmpty) 0
      else elevator.destinationManager.destinations.last._2

    if( isStraightforward(lastDestination, lastDirection) )
      controllerMode.straightforwardCost
    else
      controllerMode.notStraightforwardCost
  }

  def isStraightforward(lastDestination: Int, lastDirection: Int): Boolean = {
    val directionsEqual: Boolean = direction == lastDirection
    val floorDifference: Int =  floor - lastDestination

    if(( floorDifference > 0 && directionsEqual && direction == 1 )
      || ( floorDifference < 0 && directionsEqual && direction == -1 )
      || lastDirection == 0 ) true else false
  }
}
