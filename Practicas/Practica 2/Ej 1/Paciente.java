/**
* @author Guillermo Girón García
* @version 1.0
* @return Clase que simula al paciente de una consulta medica
*/

public class Paciente
{
  /** @param de la clase*/
  private String name, dni, adress, insuranceC;
  private long telephoneN;

  /** Builder */
  public Paciente() {}
  public Paciente(String n, String dni_, String a, long t, String ic)
  {
    name = n; dni = dni_; adress = a; telephoneN = t; insuranceC = ic;
  }

  /** Observers */
  public String getName() {return name;}
  public String getDni()  {return dni;}
  public String getAdress() {return adress;}
  public long getTelephoneN() {return telephoneN;}
  public String getInsuranceC() {return insuranceC;}
}
