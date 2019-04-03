package elevatorsystem

import org.scalatest.FunSuite
import org.mockito.Mockito.{spy, verify}

class ElevatorTest extends FunSuite {

  test("testCall") {
    val elevator = new Elevator(1,2)
    elevator.call(2,-1)
    elevator.call(4,1)
    elevator.call(5,1)
    assertResult(scala.collection.mutable.Queue((4,1),(5,1)))(elevator.destinationManager.destinations)
  }

  test("testUpdate") {
    val elevator = new Elevator(1,2)
    elevator.update(7,8)
    assertResult(7)(elevator.location)
    assertResult(8)(elevator.destination)
  }

  test("testStep") {
    val elevator: Elevator = new Elevator(1,2)
    val engine: Engine = spy(new Engine(elevator))
    elevator.engine = engine
    elevator.step()
    verify(engine).move()
  }
}
