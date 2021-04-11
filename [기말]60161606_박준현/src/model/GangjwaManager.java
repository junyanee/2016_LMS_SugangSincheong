package model;

import java.io.*;
import java.util.*;

import exception.NotFoundException;

public class GangjwaManager {
	private Vector<Gangjwa> gangjwaVector;

	public GangjwaManager() throws InputMismatchException, FileNotFoundException {
		this.gangjwaVector = new Vector<Gangjwa>();
		this.read();
	}

	private void read() throws FileNotFoundException, InputMismatchException {
		Scanner scanner;
		scanner = new Scanner(new File("data/gangjwa.txt"));
		while (scanner.hasNext()) {
			Gangjwa gangjwa = new Gangjwa();
			gangjwa.read(scanner);
			this.gangjwaVector.add(gangjwa);
		}
		scanner.close();
	}

	public Gangjwa get(int iD) throws NotFoundException {
		for (Gangjwa gangjwa : this.gangjwaVector) {
			if (gangjwa.getID() == iD) {
				return gangjwa;
			}
		}
		NotFoundException exception = new NotFoundException("°­ÁÂ" + iD);
		throw exception;
	}

	public Vector<Gangjwa> getGangjwaVector() {
		return gangjwaVector;
	}
}
