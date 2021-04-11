package model;

import java.util.*;

public class Gyosu {
	private int ID;
	private String name;

	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public void read(Scanner scanner) throws InputMismatchException {
		int iD = scanner.nextInt();
		this.setID(iD);
		String name = scanner.next();
		this.setName(name);
	}
}
