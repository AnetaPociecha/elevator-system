# Elevator System

The project aims at developing an elevator system which provides an interface presented below.
  
ElevatorSystem {<br />
&nbsp;&nbsp;&nbsp;&nbsp;def pickup(Int, Int)<br />
&nbsp;&nbsp;&nbsp;&nbsp;def update(Int, Int, Int)<br />
&nbsp;&nbsp;&nbsp;&nbsp;def step()<br />
&nbsp;&nbsp;&nbsp;&nbsp;def status(): [(Int, Int, Int), (Int, Int, Int), ...]<br />
}

System does not support calls from inside of the elevator. This feature should be cover in future.

When user executes pickup method, elevator system evaluates the cost for each elevator and assigns the call to elevator with the lowest cost. Factors such as number of stops, waiting time and straightforward are considered.
The solution was inspired by the article of Yan Dongmei https://www.researchgate.net/publication/283672652_Dispatching_strategy_of_elevator_group_control_system_based_on_policy-booking_fuzzy_optimization.

You can see an example of usage in src/main/scala/client/Client.scala.
