/*
* @author Guillermo Girón
* Practice 2: Exercise 1
*/

public class Usa_hebra
{
    public static void main(String[] args) throws InterruptedException
    {
        Thread h1 = new hebra(true), 
        h2 = new hebra(false), 
        h3 = new hebra(true), 
        h4 = new hebra(false);
        h1.start(); h2.start();
        h3.start(); h4.start();
        h4.join(); h3.join();
        h2.join(); h1.join();
        System.out.println(hebra.n);
    }
}