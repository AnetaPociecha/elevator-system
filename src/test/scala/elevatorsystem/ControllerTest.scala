package elevatorsystem

import org.scalatest.FunSuite
import org.mockito.Mockito.{spy, verify}

class ControllerTest extends FunSuite {

  test("testVerifyElevatorAvailable") {

    val elevators: List[Elevator] = List()
    val controller = new Controller(elevators)

    assertThrows[Exception] {
      controller.verifyElevatorAvailable()
    }
  }

  test("testPickElevator") {

    val elev1 = new Elevator(1,5)
    val elev2 = spy(new Elevator(1,2))
    val elevators: List[Elevator] = List(elev1, elev2)
    val controller = new Controller(elevators)

    controller.assign(1,1)

    verify(elev2).call(1,1)
  }

  test("testAssign") {
    val elev1 = new Elevator(1,5)
    val elev2 = new Elevator(1,2)
    val elevators: List[Elevator] = List(elev1, elev2)
    val controller = new Controller(elevators)

    val chosen = controller.pickElevator(1,1)

    assertResult(elev2)(chosen)
  }
}
