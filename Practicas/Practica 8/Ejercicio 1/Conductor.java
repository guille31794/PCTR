/*
* @author Guillermo Girón García
* @version 1.0
* Class that implements car drivers with mutual exclusion
*/

import java.util.concurrent.*;
import java.util.Scanner;

public class Conductor implements Runnable
{
  private String name, surname, idn, address, plate;
  private int telephone, age;

	/**
	* Default empty Conductor constructor
	*/
	public Conductor() {}

	/**
	* Default Conductor constructor
	*/
	public Conductor
  (String name, String surname, String idn, String address, String plate,
  int telephone, int age)
  {
		this.name = name;
		this.surname = surname;
		this.idn = idn;
		this.address = address;
		this.plate = plate;
		this.telephone = telephone;
		this.age = age;
	}

	/**
	* Returns value of name
	* @return
	*/
	public String getName() {	return name;}

	/**
	* Sets new value of name
	* @param
	*/
	public synchronized  void setName(String name) {	this.name = name;}

	/**
	* Returns value of surname
	* @return
	*/
	public String getSurname() {	return surname;}

	/**
	* Sets new value of surname
	* @param
	*/
	public synchronized void setSurname(String surname) {	this.surname = surname;}

	/**
	* Returns value of idn
	* @return
	*/
	public String getIdn() {	return idn;}

	/**
	* Sets new value of idn
	* @param
	*/
	public synchronized void setIdn(String idn) {	this.idn = idn;}

	/**
	* Returns value of address
	* @return
	*/
	public String getAddress() {	return address;}

	/**
	* Sets new value of address
	* @param
	*/
	public synchronized void setAddress(String address) {	this.address = address;	}

	/**
	* Returns value of plate
	* @return
	*/
	public String getPlate() {	return plate;}

	/**
	* Sets new value of plate
	* @param
	*/
	public synchronized void setPlate(String plate) {	this.plate = plate;}

	/**
	* Returns value of telephone
	* @return
	*/
	public int getTelephone() {	return telephone;}

	/**
	* Sets new value of telephone
	* @param
	*/
	public synchronized void setTelephone(int telephone)
	{	this.telephone = telephone;}

	/**
	* Returns value of age
	* @return
	*/
	public int getAge() {	return age;}

	/**
	* Sets new value of age
	* @param
	*/
	public synchronized void setAge(int age) {	this.age = age;}

	/**
	* Create string representation of Conductor for printing
	* @return
	*/
	@Override
	public String toString()
	{
		return "Conductor [name=" + name + ", surname=" + surname + ", idn=" + idn +
		", address=" + address + ", plate=" + plate + ", telephone=" + telephone +
		", age=" + age + "]";
	}

	@Override
	public void run()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Menu");
		System.out.println("1.- Set name");
		System.out.println("2.- Set idn");
		System.out.println("3.- Set adress");
		System.out.println("4.- Set telephone");
		System.out.println("5.- Set plate");
		System.out.println("6.- Set age");
		System.out.println("0.- Show info");
		System.out.print("Select Option: ");
		int option = sc.nextInt();
		switch(option)
		{
			case 1: setName(sc.nextLine());
			setSurname(sc.nextLine());
			break;
			case 2: setIdn(sc.nextLine());
			break;
			case 3: setAddress(sc.nextLine());
			break;
			case 4: setTelephone(sc.nextInt());
			break;
			case 5: setPlate(sc.nextLine());
			break;
			case 6: setAge(sc.nextInt());
			break;
			default: break;
		}
		sc.close();
		toString();
	}
}
