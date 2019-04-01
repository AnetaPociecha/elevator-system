package elevatorsystem

import org.scalatest.FunSuite

class ElevatorEngineTest extends FunSuite {

  test("testMove") {
    val elevator: Elevator = new Elevator(1,3,5)
    val engine: ElevatorEngine = new ElevatorEngine(elevator)
    elevator.call(8,1)

    engine.move()

    assertResult(4)(elevator.location)
    assertResult(5)(elevator.destination)

    engine.move()

    assertResult(5)(elevator.location)
    assertResult(5)(elevator.destination)

    engine.move()

    assertResult(5)(elevator.location)
    assertResult(8)(elevator.destination)

    engine.move()

    assertResult(6)(elevator.location)
    assertResult(8)(elevator.destination)
  }

  test("testHandleSimpleMove") {
    val elevator1: Elevator = new Elevator(1,3,6)
    val engine1: ElevatorEngine = new ElevatorEngine(elevator1)

    val elevator2: Elevator = new Elevator(1,6,3)
    val engine2: ElevatorEngine = new ElevatorEngine(elevator2)

    engine1.handleSimpleMove()
    engine2.handleSimpleMove()

    assertResult(4)(elevator1.location)
    assertResult(6)(elevator1.destination)

    assertResult(5)(elevator2.location)
    assertResult(3)(elevator2.destination)
  }

  test("testHandleStop") {
    val elevator: Elevator = new Elevator(1,3,3)
    val engine: ElevatorEngine = new ElevatorEngine(elevator)

    engine.handleStop()

    assertResult(3)(elevator.location)
    assertResult(3)(elevator.destination)

    elevator.call(5,1)
    engine.handleStop()

    assertResult(3)(elevator.location)
    assertResult(5)(elevator.destination)
  }

}
