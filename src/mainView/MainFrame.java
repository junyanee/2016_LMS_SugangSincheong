package mainView;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-access")
	public MainFrame() {
		// �θ��� ������ -> JFrame()
		super();
		// set attributes
		this.setLocation(20, 30);
		this.setSize(500, 350);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		// components
		ViewScheduler viewScheduler = new ViewScheduler();
		this.add(viewScheduler);
	}
}
