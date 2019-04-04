package elevatorsystem

import org.scalatest.FunSuite

class DestinationManagerTest extends FunSuite {

  val manager: DestinationManager = new DestinationManager()

  test("testAdd") {
    manager.add(1,1)
    manager.add(2,1)
    assertResult(scala.collection.mutable.Queue((1,1),(2,1)))(manager.destinations)
  }

  test("testNext") {
    val res: Int = manager.next()
    assertResult(1)(res)
    assertResult( scala.collection.mutable.Queue((2,1)))(manager.destinations)
  }
}
