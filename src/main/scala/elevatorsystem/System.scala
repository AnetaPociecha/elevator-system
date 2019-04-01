package elevatorsystem

trait System {

  @throws(classOf[Exception])
  def pickup(location: Int, direction: Int)
  @throws(classOf[Exception])
  def update(elevatorId: Int, location: Int, destination: Int)
  def step()
  def status(): List[(Int, Int, Int)]
}
