package panel;

import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class JPanel03 extends JPanel { // Ȱ��������ȸ �г�
	
	private JButton runningSearch;
	private JButton lookingRecord;
	private ChangingJPanel change;
	private JButton backButton;
	
	public JPanel03(ChangingJPanel change) {
		this.change = change;
		setLayout(null);
		
		backButton = new JButton("Ȩ����");
		backButton.setSize(100,60);
		backButton.setLocation(600,90);
	
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				change.ChangePanel("panel01");;
			}
		});
		
		add(backButton);
	}
}
