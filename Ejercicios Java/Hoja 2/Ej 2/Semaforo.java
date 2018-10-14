public class Semaforo
{
  private String colour;
	private int n;
  private static final String[] colours = {"red", "yellow", "green"};
  public Semaforo() {n = 0; colour = null;}

	public String getColour() {
		return colour;
	}

	public void setColour(int n) {
		if(n >= 0 && n < 3)
			this.colour = colours[n];
	}
}
