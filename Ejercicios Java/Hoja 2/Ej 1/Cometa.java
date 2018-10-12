public class Cometa extends cPlanetario
{
	private String discoverer;
	public Cometa(int s, String n, String d)	{super(s, n); discoverer = d;}


	/**
	* Returns value of discoverer
	* @return
	*/
	public String getDiscoverer() {
		return discoverer;
	}

	/**
	* Sets new value of discoverer
	* @param
	*/
	public void setDiscoverer(String discoverer) {
		this.discoverer = discoverer;
	}

	/**
	* Create string representation of Cometa for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Cometa [discoverer=" + discoverer + "," + super.toString() + "]";
	}
}
