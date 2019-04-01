package elevatorsystem

import org.scalatest.FunSuite

class ElevatorDestinationManagerTest extends FunSuite {

  val manager: ElevatorDestinationManager = new ElevatorDestinationManager()

  test("testHasNext") {
    assertResult(manager.hasNext())(false)
    manager.add(1)
    assertResult(manager.hasNext())(true)
  }

  test("testAdd") {
    manager.add(2)
    assertResult(scala.collection.mutable.Queue(1,2))(manager.destinations)
  }

  test("testForceHead") {
    manager.forceHead(3)
    assertResult(scala.collection.mutable.Queue(3,1,2))(manager.destinations)
  }

  test("testNext") {
    val res: Int = manager.next()
    assertResult(3)(res)
    assertResult( scala.collection.mutable.Queue(1,2))(manager.destinations)
  }

}
