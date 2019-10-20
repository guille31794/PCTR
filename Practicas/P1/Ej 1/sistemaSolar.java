/*
 * @author Guillermo Girón 
 * Practice 1: Exercise 1
 */

 public class sistemaSolar
 {
     public static void main(String[] args) 
     {
        sol s = new sol(1234234, "",34454);
        luna l = new luna(true, 456);
        tierra t = new tierra(32445235, 46000);
        System.out.println(t.toString()); 
        System.out.println(l.toString());
        System.out.println(s.toString());
     }
 }