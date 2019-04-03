package elevatorsystem

import org.scalatest.FunSuite
import org.mockito.Mockito.{spy, verify}

class ElevatorSystemTest extends FunSuite {

  test("testPickup") {
    val elevators: List[Elevator] = List(new Elevator(1,2), new Elevator(2,3))
    val elevatorSystem: ElevatorSystem = new ElevatorSystem(elevators)

    val controller: Controller = spy(new Controller(elevators))
    elevatorSystem.controller = controller

    val pickupValidator: Validator = spy(new Validator(elevators))
    elevatorSystem.validator = pickupValidator

    elevatorSystem.pickup(9,-1)

    verify(controller).assign(9,-1)
    verify(pickupValidator).checkPickup(9,-1)

    assertThrows[Exception] {
      elevatorSystem.pickup(-2,1)
    }
    assertThrows[Exception] {
      elevatorSystem.pickup(2,3)
    }
    assertThrows[Exception] {
      elevatorSystem.pickup(2,0)
    }
  }

  test("testStatus") {
    val elevators: List[Elevator] = List(new Elevator(1,2), new Elevator(2,3))
    val elevatorSystem: ElevatorSystem = new ElevatorSystem(elevators)

    val status = elevatorSystem.status()
    assertResult(List((2,3,3),(1,2,2)))(status)
  }

  test("testGetElevatorById") {
    val elevators: List[Elevator] = List(new Elevator(1,2), new Elevator(2,3))
    val elevatorSystem: ElevatorSystem = new ElevatorSystem(elevators)

    val elevator = elevatorSystem.getElevatorById(1)

    assertResult(1)(elevator.id)
    assertResult(2)(elevator.location)
    assertResult(2)(elevator.destination)
    assertThrows[Exception] {
      elevatorSystem.getElevatorById(3)
    }
  }

  test("testUpdate") {
    val elevators: List[Elevator] = List(new Elevator(1,2), new Elevator(2,3))
    val elevatorSystem: ElevatorSystem = new ElevatorSystem(elevators)

    elevatorSystem.update(1,4,5)
    elevatorSystem.update(1,4,5)
    val elev: Elevator = elevatorSystem.getElevatorById(1)

    assertResult(4)(elev.location)
    assertResult(5)(elev.destination)
    assertResult(0)(elev.destinationManager.destinations.length)

    assertThrows[Exception] {
      elevatorSystem.update(3,2,2)
    }
    assertThrows[Exception] {
      elevatorSystem.update(3,2,2)
    }
    assertThrows[Exception] {
      elevatorSystem.update(1,-2,2)
    }
    assertThrows[Exception] {
      elevatorSystem.update(3,2,-2)
    }
  }

  test("testStep") {
    val elev1: Elevator = spy(new Elevator(1,2))
    val elev2: Elevator = spy(new Elevator(2,3))
    val elevatorSystem: ElevatorSystem = new ElevatorSystem(List(elev1, elev2))

    elevatorSystem.step()

    verify(elev1).step()
    verify(elev2).step()
  }
}
