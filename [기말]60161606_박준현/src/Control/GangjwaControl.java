package Control;

import java.io.*;
import java.util.*;
import exception.*;
import model.*;

public class GangjwaControl {
	private GwamokManager gwamokManager;
	private GangjwaManager gangjwaManager;
	private GyosuManager gyosuManager;
	private SincheongManager sincheogManager;
	public static Vector<String> storeSincheongVector;

	public GangjwaControl() throws InputMismatchException, FileNotFoundException {
		this.gwamokManager = new GwamokManager();
		this.gangjwaManager = new GangjwaManager();
		this.gyosuManager = new GyosuManager();
		this.sincheogManager = new SincheongManager();
		
	}

	public VGangjwa getGangjwa(int id) throws NotFoundException {

		Gangjwa gangjwa = gangjwaManager.get(id);
		Gwamok gwamok = gwamokManager.get(gangjwa.getGwamokID());
		Gyosu gyosu = gyosuManager.get(gangjwa.getGyosuID());

		VGangjwa vGangjwa = new VGangjwa();
		vGangjwa.setID(gangjwa.getID());
		vGangjwa.setName(gangjwa.getName());
		vGangjwa.setGwamokname(gwamok.getName());
		vGangjwa.setGyosuname(gyosu.getName());
		return vGangjwa;
	}

	public Vector<VGangjwa> getGangjwaAll() throws NotFoundException {
		Vector<VGangjwa> vGangjwaVector = new Vector<VGangjwa>();
		Vector<Gangjwa> gangjwaVector = gangjwaManager.getGangjwaVector();
		for (Gangjwa gangjwa : gangjwaVector) {
			VGangjwa vGangjwa = this.getGangjwa(gangjwa.getID());
			vGangjwaVector.add(vGangjwa);
		}
		return vGangjwaVector;
	}

	public void setstoreSincheongVector(Vector<String> vSincheongVector) {
		storeSincheongVector = vSincheongVector;
		//System.out.println(vSincheongVector);
		//System.out.println(storeSincheongVector);
	}
	public Vector<String> getstoreSincheongVector() {
		return storeSincheongVector;
	}

	public void storeGeomsaekVector(Vector<String> sincheongVector) throws IOException {
		this.sincheogManager.storeGeomsaekVector(sincheongVector);
	}
}
