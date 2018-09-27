public class Cesar
{
  private int n_;
  private String str_;

  public Cesar(int n, String s)
  {
    n_ = n;
    str_ = new String();

    for(int i = 0; i < s.length(); ++i)
    {
      str_ += (char)(s.charAt(i) + (n % 27));
    }
  }

  public String getCesar()  {return str_;}

  public static void main(String[] args)
  {
    Cesar C = new Cesar(Integer.parseInt(args[0]), args[1]);
    System.out.println("La cadena cifrada es: " + C.getCesar());
  }
}
