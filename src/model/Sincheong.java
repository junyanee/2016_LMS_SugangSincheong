package model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Sincheong {
	String sincheong;
	
	private int ID;
	private String gwamokName;
	private String ganjwaname;

	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}
	public String getName() {return ganjwaname;}
	public void setName(String ganjwaname) {this.ganjwaname = ganjwaname;}
	public String getGwamokName() {return gwamokName;}
	public void setGwamokName(String gwamokName) {this.gwamokName = gwamokName;}
	
	public void read(Scanner scanner) throws InputMismatchException {
		int iD = scanner.nextInt();
		this.setID(iD);
		String name = scanner.next();
		this.setName(name);
	
	
}
}