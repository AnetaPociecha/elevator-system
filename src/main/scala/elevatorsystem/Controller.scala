package elevatorsystem

class Controller (
                val elevators: List[Elevator],
                val controllerMode: ControllerMode
                  = new NormalControllerMode()
                ) {

  @throws(classOf[IllegalArgumentException])
  def assign(location: Int, direction: Int): Unit = {
    val elevator: Elevator = pickElevator(location,direction)
    elevator.call(location, direction)
  }

  @throws(classOf[IllegalArgumentException])
  def pickElevator(location: Int, direction: Int): Elevator = {
    verifyElevatorAvailable()

    val costCalculator: CostCalculator = new CostCalculator(location, direction)
    val headCost: Double = costCalculator.evaluate(elevators.head)
    var minCostPair: (Elevator, Double) = (elevators.head, headCost)

    for(elevator <- elevators.tail) {
      val cost: Double = costCalculator.evaluate(elevator)
      if(cost < minCostPair._2) minCostPair = (elevator, cost)
    }
    minCostPair._1
  }

  @throws(classOf[IllegalArgumentException])
  def verifyElevatorAvailable(): Unit = {
    if(elevators.isEmpty) throw new IllegalArgumentException("Elevator does not exist")
  }
}
