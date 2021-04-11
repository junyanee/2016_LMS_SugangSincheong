
package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import Control.VGangjwa;

public class SincheongManager {
	public SincheongManager() {
	}

	public void storeGeomsaekVector(Vector<String> sincheongVector) throws IOException {
		Vector<VGangjwa> vGeomsaekVector = new Vector<VGangjwa>();
		Scanner scanner;
		for (String string : sincheongVector) {
			scanner = new Scanner(string);
			VGangjwa vGangjwa = new VGangjwa();
			vGangjwa.setID(scanner.nextInt());
			vGangjwa.setGwamokname(scanner.next());
			vGangjwa.setName(scanner.next());
			vGeomsaekVector.add(vGangjwa);
		}
		File file = new File("data/geomsaek.txt");
		file.delete();
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
		for (VGangjwa vGangjwa : vGeomsaekVector) {
			fileWriter.write(vGangjwa.getID() + " " + vGangjwa.getGwamokname() + " " + vGangjwa.getName() + "\r\n");
		}
		fileWriter.close();
	}
}
