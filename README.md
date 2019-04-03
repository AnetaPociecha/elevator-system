# Elevator System

The project aims at developing an elevator system which implements an interface presented below.
  
ElevatorSystem {
  def pickup(location: Int, direction: Int): Unit
  def update(elevatorId: Int, location: Int, destination: Int): Unit
  def step(): Unit
  def status(): List[(Int, Int, Int)] // elevatorId, location, destination
}

When user executes pickup method, elevator system evaluates the cost for each elevator and assigns the call to elevator with the lowest cost. Factors such as number of stops, waiting time and straightforward are considered.
The solution was inspired by the article of Yan Dongmei https://www.researchgate.net/publication/283672652_Dispatching_strategy_of_elevator_group_control_system_based_on_policy-booking_fuzzy_optimization.
