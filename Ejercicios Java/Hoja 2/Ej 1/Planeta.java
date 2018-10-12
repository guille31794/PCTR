import java.util.*;
public class Planeta extends cPlanetario
{
  HashSet<Satelite> satelites;
  public Planeta(int s, String n) {super(s,n); satelites = new HashSet<Satelite>();}

  public void addS(Satelite sat)
  {
    satelites.add(sat);
  }

	public void info()
  {
	  System.out.println("Planet[ " + super.toString());
    for(Satelite s : satelites)
      System.out.println(s.getName() + " y mide " + s.getSize()+ "]");
	}
}
