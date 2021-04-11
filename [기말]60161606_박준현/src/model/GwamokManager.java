package model;

import java.io.*;
import java.util.*;

import exception.NotFoundException;

public class GwamokManager {
	private Vector<Gwamok> gwamokVector;

	public GwamokManager() throws InputMismatchException, FileNotFoundException {
		this.gwamokVector = new Vector<Gwamok>();
		this.read();
	}

	private void read() throws FileNotFoundException, InputMismatchException {
		Scanner scanner;
		scanner = new Scanner(new File("data/gwamok.txt"));
		while (scanner.hasNext()) {
			Gwamok gwamok = new Gwamok();
			gwamok.read(scanner);
			this.gwamokVector.add(gwamok);
		}
		scanner.close();
	}

	public Gwamok get(int iD) throws NotFoundException {
		for (Gwamok gwamok : this.gwamokVector) {
			if (gwamok.getID() == iD) {
				return gwamok;
			}
		}
		NotFoundException exception = new NotFoundException("°ú¸ñ" + iD);
		throw exception;
	}
}
