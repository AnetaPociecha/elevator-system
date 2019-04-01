package elevatorsystem

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class CostCalculatorTest extends FunSuite {

  test("testEvaluateWaitingTime") {
    val calculator: CostCalculator = new CostCalculator(3,1)

    val elevator: Elevator = new Elevator(1,2,5)
    val res1 = calculator.evaluateWaitingTime(elevator)
    elevator.call(7,-1)
    val res2 = calculator.evaluateWaitingTime(elevator)

    assertResult(5)(res1)
    assertResult(9)(res2)
  }

  test("testDistance") {
    val calculator: CostCalculator = new CostCalculator(2,1)
    val res1 = calculator.distance(1,5)
    val res2 = calculator.distance(5,2)
    val res3 = calculator.distance(5,5)

    assertResult(4)(res1)
    assertResult(3)(res2)
    assertResult(0)(res3)
  }

  test("testIsStraightforward") {

    val calculator1: CostCalculator = new CostCalculator(3,1)
    val res1: Boolean = calculator1.isStraightforward(5,1)
    val res2: Boolean = calculator1.isStraightforward(2,1)

    val calculator2: CostCalculator = new CostCalculator(3,-1)
    val res3: Boolean = calculator2.isStraightforward(1,1)
    val res4: Boolean = calculator2.isStraightforward(2,-1)

    assert(res1)
    assert(!res2)
    assert(!res3)
    assert(res4)
  }

  test("testEvaluateStraightforward") {
    val calculator: CostCalculator = new CostCalculator(3,1)

    val elevator1: Elevator = new Elevator(1,2,5)
    val elevator2: Elevator = new Elevator(1,2,0)

    val res1 = calculator.evaluateStraightforward(elevator1)
    val res2 = calculator.evaluateStraightforward(elevator2)
    elevator1.call(2,1)
    val res3 = calculator.evaluateStraightforward(elevator1)
    elevator1.call(5,-1)
    val res4 = calculator.evaluateStraightforward(elevator1)

    assertResult(calculator.controllerMode.straightforwardCost)(res1)
    assertResult(calculator.controllerMode.notStraightforwardCost)(res2)
    assertResult(calculator.controllerMode.straightforwardCost)(res3)
    assertResult(calculator.controllerMode.notStraightforwardCost)(res4)
  }


  test("testEvaluateNumberOfStops") {
    val calculator: CostCalculator = new CostCalculator(2,1)
    val elevator: Elevator = new Elevator(1,2,4)

    elevator.call(5,1)
    val res1 = calculator.evaluateNumberOfStops(elevator)

    elevator.call(4,1)
    val res2 = calculator.evaluateNumberOfStops(elevator)

    assertResult(1)(res1)
    assertResult(2)(res2)
  }

  test("testEvaluate") {
    val calculator: CostCalculator = new CostCalculator(2,1)
    val elevator: Elevator = new Elevator(1,0,0)

    val res = calculator.evaluate(elevator)

    res should be (1.4 +- 0.0001)
  }
}
