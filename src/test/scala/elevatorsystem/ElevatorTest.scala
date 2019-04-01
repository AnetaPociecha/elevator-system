package elevatorsystem

import org.scalatest.FunSuite

class ElevatorTest extends FunSuite {

  test("testCall") {
    val elevator = new Elevator(1,2,3)
    elevator.call(4,1)
    elevator.call(5,1)
    assertResult(scala.collection.mutable.Queue(4,5))(elevator.destinationManager.destinations)
  }

  test("testUpdate") {
    val elevator = new Elevator(1,2,3)
    elevator.update(7,8)
    assertResult(7)(elevator.location)
    assertResult(8)(elevator.destination)
  }

  test("testStep") {

  }

}
