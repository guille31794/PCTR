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
      str_ += (char)(s.charAt(i) - (n % 26));
    }
  }

  public String getDesCesar()  {return str_;}

  public static void main(String[] args)
  {
    for(int i = 1; i < 26; ++i)
    {
      desCesar C = new desCesar(i, args[0]);
      System.out.println("La cadena descifrada es: " + C.getDesCesar());
    }
  }
}
