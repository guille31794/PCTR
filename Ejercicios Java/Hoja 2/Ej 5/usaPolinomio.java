
public class usaPolinomio
{
  public static void main(String[] args)
  {
    Polinomio p1, p2, p3;
    p1 = new Polinomio(3);
    p1.setRandomP();
    p2 = new Polinomio(5);
    p2.setRandomP();
    p3 = p2.prod(2);
    System.out.println(p3.toString());
  }
}
