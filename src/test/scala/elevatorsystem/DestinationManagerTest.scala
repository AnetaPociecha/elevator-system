package elevatorsystem

import org.scalatest.FunSuite

class DestinationManagerTest extends FunSuite {

  val manager: DestinationManager = new DestinationManager()

  test("testHasNext") {
    assertResult(manager.hasNext())(false)
    manager.add(1,1)
    assertResult(manager.hasNext())(true)
  }

  test("testAdd") {
    manager.add(2,1)
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
