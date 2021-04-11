package sugangSincheongViews;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import Control.*;
import exception.*;
import login.LoginView;
import mainView.*;

public class SincheongPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public static VGangjwa vgangjwa;
	public GangjwaControl gangjwaControl;
	private Vector<VGangjwa> vGangjwaVector;
	private Vector<String> vSincheongVector;
	private PersonalInfoPanel personalInfoPanel;
	private GangjwaSelectionPanel GangjwaSelectionPanel;
	private CommandPanel commandPanel;
	private ScheduleListener scheduleHandler;
	public LoginView loginview;
	
	public SincheongPanel(ScheduleListener scheduleHandler) {
		super();
	
		try {
			this.scheduleHandler = scheduleHandler;
			this.gangjwaControl = new GangjwaControl();
			this.vGangjwaVector = gangjwaControl.getGangjwaAll();
			this.vSincheongVector = new Vector<String>();

			// LayoutManager
			LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(layoutManager);			
			
			this.personalInfoPanel =  new PersonalInfoPanel();
			this.add(this.personalInfoPanel);			
			this.GangjwaSelectionPanel = new GangjwaSelectionPanel(this.vGangjwaVector);			
			this.add(this.GangjwaSelectionPanel);
			this.commandPanel = new CommandPanel();
			this.add(this.commandPanel);
			
		} catch (InputMismatchException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	//storeSincheongVector
	public void storeSincheongVector(Vector<String> vSincheongVector) {
		//System.out.println(vSincheongVector);
		this.gangjwaControl.setstoreSincheongVector( vSincheongVector);
	}
	
	//CommandPanel - 버튼 OK, Cancel, Exit
	private class CommandPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private JButton ok, logout, exit;
		public CommandPanel() {
			ActionHandler actionHandler = new ActionHandler();
			
			this.ok = new JButton("OK");
			this.ok.addActionListener(actionHandler);
			this.add(this.ok);
			
			this.logout = new JButton("로그아웃");
			this.logout.addActionListener(actionHandler);
			this.add(this.logout);
			
			this.exit = new JButton("Exit");
			this.exit.addActionListener(actionHandler);
			this.add(this.exit);
		}
		
		//ActionHandler ActionListener
		private class ActionHandler implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				if (event.getSource().equals(ok)) {
					for(String string : GangjwaSelectionPanel.rightStringVector)
					{
						vSincheongVector.add(string);
					}
					//System.out.println(vSincheongVector);
					storeSincheongVector(vSincheongVector);
					scheduleHandler.doNext(ok.getText());
				} else if (event.getSource().equals(logout)) {
					scheduleHandler.doNext(logout.getText());
				} else if (event.getSource().equals(exit)) {
					scheduleHandler.doNext(exit.getText());
				}
			}
		}
	}
	
	//PersonalInfoPanel - 이름,학과,학번
	private class PersonalInfoPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		public PersonalInfoPanel() {
			super();
					
			LoginView loginview = new LoginView(scheduleHandler);
			
			JLabel lName = new JLabel ("<이름 : 박준현>");
			this.add(lName);
			LoginView.LoginPanel.class.getName();
			
			JLabel lDepartment = new JLabel ("<학과 : 융합소프트웨어>");
			this.add(lDepartment);
			//this.add(departmentText);
			
			JLabel lID = new JLabel ("<학번 : 60161606>");
			this.add(lID);
			//this.add(idText);
		}
	}
	
	// GangjwaSelectionPanel
	public static class GangjwaSelectionPanel  extends JPanel {
		private static final long serialVersionUID = 1L;
		private KeyboardHandler keyboardHandler;
		private MouseHandler mouseHandler;
		//private MouseMotionHandler mousemotionHandler;
		
		private Vector<String> leftStringVector;
		private LeftList leftList;	
		private ButtonPanel buttonPanel;		
		public Vector<String> rightStringVector;
		public RightList rightList;
		

		//GangjwaSelectionPanel - Vector
		public GangjwaSelectionPanel(Vector<VGangjwa> vGangjwaVector) {
			super();
			this.leftStringVector = new Vector<String>();
			for (VGangjwa vGangjwa: vGangjwaVector) {
				this.leftStringVector.add(vGangjwa.getID()+" "+
					vGangjwa.getGwamokname()+" "+vGangjwa.getName());
			}
			
			this.keyboardHandler = new KeyboardHandler();
			this.mouseHandler = new MouseHandler();
			//this.mousemotionHandler = new MouseMotionHandler();
					
			this.leftList = new LeftList(this.leftStringVector);
			this.leftList.addMouseListener(this.mouseHandler);
			this.leftList.addKeyListener(this.keyboardHandler);
			//this.leftList.addMouseMotionListener(this.mousemotionHandler);
			this.add(leftList);
			
			this.buttonPanel = new ButtonPanel();
			this.add(this.buttonPanel);
			
			this.rightStringVector = new Vector<String>();
			this.rightList = new RightList(this.rightStringVector);
			this.rightList.addMouseListener(this.mouseHandler);
			this.rightList.addKeyListener(this.keyboardHandler);
			this.add(this.rightList);	
		}
		
		//왼쪽선택
		private void selectLeft() {
			this.rightList.clearSelection();
			this.buttonPanel.getRightButton().setEnabled(true);
			this.buttonPanel.getLeftButton().setEnabled(false);
		}
		//오른쪽선택 
		private void selectRight() {
			this.leftList.clearSelection();
			this.buttonPanel.getRightButton().setEnabled(false);
			this.buttonPanel.getLeftButton().setEnabled(true);
		}
		//focus 왼쪽으로 넘기기
		private void moveSelectionToLeft() {
			int index = this.rightList.getSelectedIndex();
			this.rightList.clearSelection();
			if (index>this.leftList.getLastVisibleIndex()) {
				index = this.leftList.getLastVisibleIndex();
			}
			this.leftList.setSelectedIndex(index);
			this.leftList.requestFocus();
		}
		//focus 오른쪽으로 넘기기
		private void moveSelectionToRight() {
			int index = this.leftList.getSelectedIndex();
			this.leftList.clearSelection();
			if (index>this.rightList.getLastVisibleIndex()) {
				index = this.rightList.getLastVisibleIndex();
			}
			this.rightList.setSelectedIndex(index);
			this.rightList.requestFocus();
		}

		// 왼쪽으로 넘기기 moveToLeft rightList -> leftList
		private void moveToLeft() {
			int[] indices = rightList.getSelectedIndices();
			int index = 0;
			for (int i= indices.length-1; i>=0; i--) {
				index = indices[i];
				leftStringVector.add(rightStringVector.remove(index));
				leftList.setListData(leftStringVector);
				rightList.setListData(rightStringVector);
			}
			if (this.rightStringVector.size()==0) {
				this.buttonPanel.getRightButton().setEnabled(true);
				this.buttonPanel.getLeftButton().setEnabled(false);
				this.leftList.setSelectedIndex(0);
				this.leftList.requestFocus();
				return;
			}
			if (index>this.rightList.getLastVisibleIndex()) {
				index = this.rightList.getLastVisibleIndex();
			}
			this.rightList.setSelectedIndex(index);
		}
		
		// 오른쪽으로 넘기기 moveToRight leftList -> rightList
		private void moveToRight() {
			int[] indices = leftList.getSelectedIndices();
			int index = 0;
			for (int i= indices.length-1; i>=0; i--) {
				index = indices[i];
				rightStringVector.add(leftStringVector.remove(index));
				leftList.setListData(leftStringVector);
				rightList.setListData(rightStringVector);
			}
			if (this.leftStringVector.size()==0) {
				this.buttonPanel.getRightButton().setEnabled(false);
				this.buttonPanel.getLeftButton().setEnabled(true);
				this.rightList.setSelectedIndex(0);
				this.rightList.requestFocus();
				return;
			}
			if (index>this.leftList.getLastVisibleIndex()) {
				index = this.leftList.getLastVisibleIndex();
			}
			this.leftList.setSelectedIndex(index);
		}
		
		// LeftList
		private class LeftList extends JList<String> {
			private static final long serialVersionUID = 1L;
			public LeftList(Vector<String> stringVector) {
				super(stringVector);				
				this.setPreferredSize(new Dimension(200, 200));
				
			}			
		}
		
		// RightList
		private class RightList extends JList<String> {
			private static final long serialVersionUID = 1L;
			public RightList(Vector<String> stringVector) {
				super(stringVector);
				this.setPreferredSize(new Dimension(200, 200));
			}			
		}
		
		// ButtonPanel >> , <<
		private class ButtonPanel extends JPanel {
			private static final long serialVersionUID = 1L;
			
			private JButton rightButton, leftButton;
			public JButton getRightButton() { return this.rightButton; }
			public JButton getLeftButton() { return this.leftButton; }
			
			public ButtonPanel() {
				LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
				this.setLayout(layoutManager);
				
				ActionHandler actionHandler = new ActionHandler();
				
				this.rightButton = new JButton(">>");
				this.rightButton.addActionListener(actionHandler);
				this.rightButton.addKeyListener(keyboardHandler);
				this.add(this.rightButton);
				this.rightButton.setEnabled(false);
				
				this.leftButton = new JButton("<<");
				this.leftButton.addActionListener(actionHandler);
				this.leftButton.addKeyListener(keyboardHandler);
				this.add(this.leftButton);
				this.leftButton.setEnabled(false);
			}
			// ActionHandler - moveToRight / moveToLeft
			private class ActionHandler implements ActionListener {
				public void actionPerformed(ActionEvent event) {
					if (event.getSource().equals(rightButton)) {
						moveToRight();
					} else if (event.getSource().equals(leftButton)) {
						moveToLeft();
					}					
				}				
			}
		}
		// MouseHandler
		private class MouseHandler implements MouseListener {
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount()==1) {
					if (event.getSource().equals(leftList)) {
						selectLeft();
					} else if (event.getSource().equals(rightList)) {
						selectRight();
					}
				}
				else if (event.getClickCount()==2) {
					if (event.getSource().equals(leftList)) {
						moveToRight();
					} else if (event.getSource().equals(rightList)) {
						moveToLeft();
					}					
				}				
			}
			public void mouseEntered(MouseEvent event) {}
			public void mouseExited(MouseEvent event) {}
			public void mousePressed(MouseEvent event) {}
			public void mouseReleased(MouseEvent event) {}
			
		}
		// KeyboardHandler
		private class KeyboardHandler implements KeyListener {

			public void keyPressed(KeyEvent event) {
				if (event.getSource().equals(leftList)
						||event.getSource().equals(buttonPanel.getRightButton())) {
					if (event.getKeyCode() == KeyEvent.VK_ENTER) {
						moveToRight();
					} else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
						moveSelectionToRight();
					}
					
				} else if (event.getSource().equals(rightList)
						||event.getSource().equals(buttonPanel.getLeftButton())) {
					if (event.getKeyCode() == KeyEvent.VK_ENTER) {
						moveToLeft();
					} else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
						moveSelectionToLeft();
					}					
				}
			}
			public void keyReleased(KeyEvent event) {}
			public void keyTyped(KeyEvent event) {}			
		}
	}
}
