public class desCesar
{
  private int n_;
  private String str_;

  public desCesar(int n, String s)
  {
    n_ = n;
    str_ = new String();

    for(int i = 0; i < s.length(); ++i)
    {
      str_ += (char)(s.charAt(i) - (n % 27));
    }
  }

  public String getDesCesar()  {return str_;}

  public static void main(String[] args)
  {
    desCesar C = new desCesar(Integer.parseInt(args[0]), args[1]);
    System.out.println("La cadena descifrada es: " + C.getDesCesar());
  }
}
