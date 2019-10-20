/*
 * @author Guillermo Girón 
 * Practice 1: Exercise 1
 */

 public class estrella extends cuerpoPl
 {
    private int edad;
    private String nombre;

    public estrella(int e, String n, int d)
    {
        super(d);
        edad = e;
        nombre = n;
    }

    public String nombre()
    {
        return nombre;
    }

    public int edad()
    {
        return edad;
    }
 }