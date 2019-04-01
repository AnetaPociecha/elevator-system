package elevatorsystem

trait ControllerMode {
  val waitingTimeWeight: Double
  val numberOfStopsWeight: Double
  val straightforwardWeight: Double
  val notStraightforwardCost: Double
  val straightforwardCost: Double
}
