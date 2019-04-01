package client

import elevatorsystem.{Elevator, ElevatorSystem}

object Client {

  val elevators: List[Elevator] = List(
    new Elevator(1,2,2),
    new Elevator(2,0,0)
  )
  val elevatorSystem: ElevatorSystem
    = new ElevatorSystem(elevators)

  def main(args: Array[String]): Unit = {

    var status = elevatorSystem.status()
    println(status)

    elevatorSystem.pickup(4,1)
    elevatorSystem.step()
    elevatorSystem.step()

    status = elevatorSystem.status()
    println(status)

    elevatorSystem.update(2,4,6)

    status = elevatorSystem.status()
    println(status)
  }
}
