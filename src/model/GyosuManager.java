package model;

import java.io.*;
import java.util.*;

import exception.NotFoundException;

public class GyosuManager {
	private Vector<Gyosu> gyosuVector;

	public GyosuManager() throws InputMismatchException, FileNotFoundException {
		this.gyosuVector = new Vector<Gyosu>();
		this.read();
	}

	private void read() throws FileNotFoundException, InputMismatchException {
		Scanner scanner;
		scanner = new Scanner(new File("data/gyosu.txt"));
		while (scanner.hasNext()) {
			Gyosu gyosu = new Gyosu();
			gyosu.read(scanner);
			this.gyosuVector.add(gyosu);
		}
		scanner.close();
	}

	public Gyosu get(int iD) throws NotFoundException {
		for (Gyosu gyosu : this.gyosuVector) {
			if (gyosu.getID() == iD) {
				return gyosu;
			}
		}
		NotFoundException exception = new NotFoundException("±³¼ö" + iD);
		throw exception;
	}
}
