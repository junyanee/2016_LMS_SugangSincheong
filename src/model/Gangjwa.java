package model;

import java.util.*;

public class Gangjwa {
	
	private int ID;
	private String name;
	private int gwamokID;
	private int gyosuID;

	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getGwamokID() {return gwamokID;}
	public void setGwamokID(int gwamokID) {this.gwamokID = gwamokID;}
	public int getGyosuID() {return gyosuID;}
	public void setGyosuID(int gyosuID) {this.gyosuID = gyosuID;}

	public void read(Scanner scanner) throws InputMismatchException {
		int id = scanner.nextInt();
		this.setID(id);
		String name = scanner.next();
		this.setName(name);
		int gwamokID = scanner.nextInt();
		this.setGwamokID(gwamokID);
		int gyosuID = scanner.nextInt();
		this.setGyosuID(gyosuID);
	}
}
