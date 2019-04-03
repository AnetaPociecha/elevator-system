package elevatorsystem

import org.scalatest.FunSuite

class ValidatorTest extends FunSuite {

  test("testCheckPickup") {
    val elevator1: Elevator = new Elevator(1,1)
    val elevator2: Elevator = new Elevator(2,5)
    elevator1.call(2,1)
    elevator1.call(3,-1)
    elevator1.call(3,1)
    val validator: Validator = new Validator(List(elevator1,elevator2))

    val res1 = validator.checkPickup(3,1)
    val res2 = validator.checkPickup(4,1)

    assert(!res1)
    assert(res2)
  }
}
