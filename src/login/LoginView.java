package login;

import java.awt.*;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import mainView.ScheduleListener;
import mainView.ViewScheduler;



public class LoginView extends JPanel{
   private static final long serialVersionUID = 1L;
   
   private LoginPanel loginPanel;

   public LoginView(ScheduleListener scheduleHandler) {
      super();
          
   this.loginPanel = new LoginPanel(scheduleHandler);
   this.add(loginPanel);
}
   
public class LoginPanel extends JPanel {
   private static final long serialVersionUID = 1L;
   
   private JButton reset, login;
   public JTextField idText, nameText, departmentText;
   private JPasswordField passwordText;
   private ScheduleListener scheduleHandler;

   private ViewScheduler viewscheduler;
   
   
   public LoginPanel(ScheduleListener scheduleHandler) {
	   super();
      this.scheduleHandler = scheduleHandler;
      LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
      this.setLayout(layoutManager);
      ActionHandler actionHandler = new ActionHandler();
      KeyHandler keyHandler = new KeyHandler();
      JPanel Divider = new JPanel();
      JPanel Divider1 = new JPanel();
      JPanel Divider2 = new JPanel();
      JPanel Divider3 = new JPanel();
      JLabel info = new JLabel("로그인을 해주세요.");
      
      this.add(Divider);
      this.add(info);
      this.add(Divider1);
      
      JLabel id = new JLabel ("ID");
      this.add(id);
      this.idText = new JTextField(10);
      this.add(idText);
      
      JLabel password = new JLabel ("Password");
      this.add(password);
      this.passwordText = new JPasswordField(10);
      this.passwordText.addKeyListener(keyHandler);
      this.add(passwordText);
      
      //Divider
      this.add(Divider2);
      
      this.reset = new JButton("지우기") ;
      this.reset.addActionListener(actionHandler);
      reset.setBackground(Color.CYAN);
      this.add(reset);
      
      //Divider1
      this.add(Divider3);
      
      this.login = new JButton("로그인") ;
      this.login.addActionListener(actionHandler);
      login.setBackground(Color.CYAN);
      this.add(login);
      
   }
   
private class ActionHandler implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         event.getActionCommand();
         if (event.getSource().equals(login)) {
            LoginCheck();
         }else if (event.getSource().equals(reset)){
            idText.setText("");
            passwordText.setText("");
         }
      }
   }

private class KeyHandler implements KeyListener {

	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) 
		{		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) 
			LoginCheck();
		}
	public void keyReleased(KeyEvent e) {}	
}
   
   public void LoginCheck(){
         new JOptionPane();
         
         if(idText.getText().equals("1") && new String(passwordText.getPassword()).equals("1")){
            JOptionPane.showMessageDialog(this, "로그인이 완료되었습니다", "로그인 성공" , JOptionPane.INFORMATION_MESSAGE);
            scheduleHandler.doNext(login.getText());
           
         }else{
            JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호를 확인해 주세요", "로그인 실패" , JOptionPane.WARNING_MESSAGE);
               idText.setText("");
               passwordText.setText("");            
         }
      }
	public ViewScheduler getViewscheduler() {
		return viewscheduler;
	}
	public void setViewscheduler(ViewScheduler viewscheduler) {
		this.viewscheduler = viewscheduler;
	}
      
      
}
}