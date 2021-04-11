package model;

import java.util.*;

public class Gwamok {

	private int ID; 
	private String name; 
	private String content;

	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}

	public void read(Scanner scanner) throws InputMismatchException {
		int id = scanner.nextInt();
		this.setID(id);
		String name = scanner.next();
		this.setName(name);
	}
}
