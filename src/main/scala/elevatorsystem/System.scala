package elevatorsystem

trait System {

  @throws(classOf[IllegalArgumentException])
  def pickup(location: Int, direction: Int)
  @throws(classOf[IllegalArgumentException])
  def update(elevatorId: Int, location: Int, destination: Int)
  def step()
  def status(): List[(Int, Int, Int)]
}
