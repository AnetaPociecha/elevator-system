package elevatorsystem

import org.scalatest.FunSuite

class ElevatorSystemTest extends FunSuite {

  val elevators: List[Elevator] = List(new Elevator(1,2,3), new Elevator(2,3,4))
  val elevatorSystem: ElevatorSystem = new ElevatorSystem(elevators)

  test("testPickup") {
    assertThrows[Exception] {
      elevatorSystem.pickup(-2,1)
    }
    assertThrows[Exception] {
      elevatorSystem.pickup(2,3)
    }
  }

  test("testUpdate") {

  }

  test("testStep") {

  }

  test("testStatus") {
    val status = elevatorSystem.status()
    assertResult(List((2,3,4),(1,2,3)))(status)
  }

  test("testGetElevatorById") {
    val elevator = elevatorSystem.getElevatorById(1)
    assertResult(1)(elevator.id)
    assertResult(2)(elevator.location)
    assertResult(3)(elevator.destination)
    assertThrows[Exception] {
      elevatorSystem.getElevatorById(3)
    }
  }

}
