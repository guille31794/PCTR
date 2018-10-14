
public class usaMatriz
{
  public static void main(String[] args)
  {
    Matriz M1, M2, M3;
    M1 = new Matriz(3, 3);
    M2 = new Matriz(3, 3);
    M1.randomSetMatrix();
    M2.randomSetMatrix();
    M3 = M1.sub(M2);
    M1.print();
    System.out.println("-");
    M2.print();
    System.out.println("-----------");
    M3.print();
  }
}
