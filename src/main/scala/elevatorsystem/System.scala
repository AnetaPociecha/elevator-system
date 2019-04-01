package elevatorsystem

trait System {

  @throws(classOf[Exception])
  def pickup(location: Int, direction: Int) // piętro zgłoszenia oraz kierunek (ujemny oznacza dół, dodatni oznacza górę)
  @throws(classOf[Exception])
  def update(elevatorId: Int, location: Int, destination: Int) // (ID windy, obecny numer piętra, docelowy numer piętra)
  def step()
  def status(): List[(Int, Int, Int)]
}
