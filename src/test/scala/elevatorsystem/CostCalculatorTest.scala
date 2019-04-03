package elevatorsystem

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class CostCalculatorTest extends FunSuite {

  test("testEvaluateWaitingTime") {
    val calculator: CostCalculator = new CostCalculator(3,1)

    val elevator: Elevator = new Elevator(1,2)
    elevator.call(5,1)
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
    val res1: Boolean = calculator1.isStraightforward(2,1)
    val res2: Boolean = calculator1.isStraightforward(4,1)

    val calculator2: CostCalculator = new CostCalculator(3,-1)
    val res3: Boolean = calculator2.isStraightforward(1,1)
    val res4: Boolean = calculator2.isStraightforward(5,-1)

    assert(res1)
    assert(!res2)
    assert(!res3)
    assert(res4)
  }

  test("testEvaluateStraightforward") {
    val calculator: CostCalculator = new CostCalculator(3,1)
    val elevator1: Elevator = new Elevator(1,4)

    val res1 = calculator.evaluateStraightforward(elevator1)
    assertResult(calculator.controllerMode.straightforwardCost)(res1)

    elevator1.call(5,1)
    val res2 = calculator.evaluateStraightforward(elevator1)
    assertResult(calculator.controllerMode.notStraightforwardCost)(res2)

    elevator1.call(4,-1)
    val res3 = calculator.evaluateStraightforward(elevator1)
    assertResult(calculator.controllerMode.notStraightforwardCost)(res3)

    elevator1.call(5,-1)
    val res4 = calculator.evaluateStraightforward(elevator1)
    assertResult(calculator.controllerMode.notStraightforwardCost)(res4)
  }


  test("testEvaluateNumberOfStops") {
    val calculator: CostCalculator = new CostCalculator(2,1)
    val elevator: Elevator = new Elevator(1,2)
    elevator.call(4,1)
    elevator.call(5,1)
    val res1 = calculator.evaluateNumberOfStops(elevator)

    elevator.call(4,1)
    val res2 = calculator.evaluateNumberOfStops(elevator)

    assertResult(2)(res1)
    assertResult(3)(res2)
  }

  test("testEvaluate") {
    val calculator: CostCalculator = new CostCalculator(2,1)
    val elevator: Elevator = new Elevator(1,0)

    val res1: Double = calculator.evaluate(elevator)
    elevator.call(1,-1)
    val res2: Double = calculator.evaluate(elevator)

    res1 should be (0.8 +- 0.0001)
    res2 should be (1.8 +- 0.0001)
  }
}
