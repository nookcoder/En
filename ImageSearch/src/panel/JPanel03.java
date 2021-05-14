package panel;

import java.awt.Graphics;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import main.Constants;
import main.SearchLog;

public class JPanel03 extends JPanel { // 활동내역조회 패널
	
	private SearchLog log;
	private ChangingJPanel change;

	private JButton deletButton;
	private JButton backButton;
	private JButton showLogButton;
	private JTextArea logArea; 
	private JScrollPane jscroll;
	private Constants constant = new Constants();
	
	public JPanel03(ChangingJPanel change) {
		this.change = change;
		this.log = new SearchLog();
		
		setLayout(null);
		MakeBackButton();
		MakeDeleteButton();
		MakeShowLogButton();
		
		logArea= new JTextArea("");
		logArea.setEditable(false);
		
		jscroll = new JScrollPane(logArea);
		jscroll.setBounds(0,30,400,400);
		
		add(backButton); add(deletButton); add(showLogButton); add(jscroll);
	}
	
	public void MakeBackButton() {
		backButton = new JButton("홈으로");
		backButton.setSize(60,60);
		backButton.setLocation(160,450);
		constant.DecorateButtonJpanel02(backButton);
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				change.ChangePanel("panel01");
			}
		});
		
		add(backButton);
		
	}
	
	public void MakeDeleteButton() {
		deletButton = new JButton("삭제");
		deletButton.setSize(60,60);
		deletButton.setLocation(300,450);
		constant.DecorateButtonJpanel02(deletButton);
		
		deletButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					log.DeleteAll();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		add(deletButton);
	}
	
	public void MakeShowLogButton()
	{
		showLogButton = new JButton("조회");
		showLogButton.setSize(60,60);
		showLogButton.setLocation(20,450);
		constant.DecorateButtonJpanel02(showLogButton);
		
		showLogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					String str;
					logArea.selectAll();
					logArea.replaceSelection("");
					
					str = log.GetSearchLogString();
					logArea.append(str);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		 add(showLogButton);
	}
}
