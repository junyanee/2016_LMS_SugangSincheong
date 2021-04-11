package mainView;

import java.awt.*;

import javax.swing.*;

import login.LoginView;
import sugangSincheongViews.*;

public class ViewScheduler extends JPanel {
	private static final long serialVersionUID = 1L;
	private LoginView login;
	private SincheongPanel sincheongPanel;
	private GeomsaekPanel geomsaekPanel;
	public CardLayout cardLayout;
	ScheduleHandler scheduleHandler;
	public ViewScheduler() {
		this.cardLayout = new CardLayout();
		this.setLayout(cardLayout);

		scheduleHandler = new ScheduleHandler();
		
		this.login = new LoginView(scheduleHandler);
		this.add(this.login, "로그인");
				
		this.geomsaekPanel = new GeomsaekPanel(scheduleHandler);
		this.add(this.geomsaekPanel, "geomsaekPanel");
		
		this.sincheongPanel = new SincheongPanel(scheduleHandler);
		this.add(this.sincheongPanel, "sincheongPanel");

		this.cardLayout.show(this, "로그인");
	}

	private void schedule(String command) {
		if(command.equals("로그인")){
			cardLayout.show(this, "sincheongPanel");
		}
		else if (command.equals("OK")) {
			scheduleHandler = new ScheduleHandler();
			this.geomsaekPanel = new GeomsaekPanel(scheduleHandler);
			this.add(this.geomsaekPanel, "geomsaekPanel");
			cardLayout.show(this, "geomsaekPanel");
		}
		else if(command.equals("로그아웃")){
			cardLayout.show(this, "로그인");	
		}
		else if(command.equals("Exit")){
			System.exit(0);
		}
		else if(command.equals("뒤로가기")){
			cardLayout.show(this, "sincheongPanel");
		}
	}

	public class ScheduleHandler implements ScheduleListener {
		public void doNext(String command) {
			schedule(command);
		}
	}
}
