package sugangSincheongViews;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
import Control.*;
import mainView.*;

public class GeomsaekPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static GangjwaControl gangjwaControl;
	public SincheongPanel sincheongPanel;
	public ButtonPanel buttonPanel;
	public Vector<String> sincheongVector;
	public ScheduleListener scheduleHandler;
	public CardLayout cardLayout;

	public GeomsaekPanel(ScheduleListener scheduleHandler) {
		super();

		try {
			gangjwaControl = new GangjwaControl();
			this.scheduleHandler = scheduleHandler;

			JLabel finishmessage = new JLabel("��û��� �Դϴ�  >> ");
			JPanel Divider = new JPanel();

			this.add(finishmessage);
			this.add(Divider);
			this.sincheongPanel = new SincheongPanel();
			this.add(this.sincheongPanel);
			this.buttonPanel = new ButtonPanel();
			this.add(this.buttonPanel);
		} catch (InputMismatchException | FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void storeGeomsaekVector() {
		try {
			GeomsaekPanel.gangjwaControl.storeGeomsaekVector(this.sincheongVector);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class SincheongPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		public SincheongList sincheongList;

		public SincheongPanel() {

			sincheongVector = new Vector<String>();
			sincheongVector = gangjwaControl.getstoreSincheongVector();
			sincheongList = new SincheongList(sincheongVector);
			this.add(sincheongList);

			// System.out.println(gangjwaControl.getstoreSincheongVector());

		}
	}

	private class ButtonPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private JButton back;
		private JButton save;
		private JButton eexit;

		public ButtonPanel() {

			ActionHandler actionHandler = new ActionHandler();

			this.back = new JButton("�ڷΰ���");
			this.back.addActionListener(actionHandler);
			this.add(this.back);

			this.save = new JButton("����");
			this.save.addActionListener(actionHandler);
			this.add(this.save);

			this.eexit = new JButton("����");
			this.eexit.addActionListener(actionHandler);
			this.add(this.eexit);

			this.add(save);
			this.add(back);
			this.add(eexit);

		}

		private class ActionHandler implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				event.getActionCommand();
				if (event.getSource().equals(back)) {
					scheduleHandler.doNext(back.getText());
				} else if (event.getSource().equals(save)) {
					storeGeomsaekVector();
					JOptionPane.showMessageDialog(buttonPanel, "������ �Ϸ�Ǿ����ϴ�", "���� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
				} else if (event.getSource().equals(eexit)) {
					int option = JOptionPane.showConfirmDialog(buttonPanel, "���� �� �����Ͻðڽ��ϱ�?", "����",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (option == 0) {
						storeGeomsaekVector();
						JOptionPane.showMessageDialog(buttonPanel, "������ �Ϸ�Ǿ����ϴ�. �����մϴ�", "���� �� ����",
								JOptionPane.INFORMATION_MESSAGE);
						System.exit(1);
					} else if (option == 1) {
						System.exit(0);
					}
				}
			}
		}
	}

	public class SincheongList extends JList<String> {
		private static final long serialVersionUID = 1L;

		public SincheongList(Vector<String> stringVector) {
			super(stringVector);

			this.setPreferredSize(new Dimension(200, 200));
		}
	}

}
