import java.util.Scanner;

public class usacPlanetario
{
  public static void main(String[] args)
  {
    Satelite c1 = new Satelite(0, "Luna", 28);
    cPlanetario c2 = new Planeta(46, "Tierra");
    ((Planeta)c2).addS(c1);
    ((Planeta)c2).info();
  }
}
