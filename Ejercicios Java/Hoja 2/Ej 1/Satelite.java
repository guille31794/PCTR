public class Satelite extends cPlanetario
{
  private int orbit;
  public Satelite(int s, String n, int o) {super(s, n); orbit = o;}

	/**
	* Returns value of orbit
	* @return
	*/
	public int getOrbit() {
		return orbit;
	}

	/**
	* Sets new value of orbit
	* @param
	*/
	public void setOrbit(int orbit) {
		this.orbit = orbit;
	}

	/**
	* Create string representation of Satelite for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Satelite [orbit=" + orbit + " " + super.toString() +"]";
	}
}
