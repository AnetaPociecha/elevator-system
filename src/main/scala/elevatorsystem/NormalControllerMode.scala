package elevatorsystem

class NormalControllerMode(
                            val waitingTimeWeight: Double = 0.4,
                            val numberOfStopsWeight: Double = 0.4,
                            val straightforwardWeight: Double = 0.2,
                            val notStraightforwardCost: Double = 3,
                            val straightforwardCost: Double = 0
                          ) extends ControllerMode {}
