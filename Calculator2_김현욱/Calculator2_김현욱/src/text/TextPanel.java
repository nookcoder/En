package text;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

import keypad.KeyPadPanel;
import set.Constants;

public class TextPanel extends JPanel{
	
	// ���� ���� �ִ� �������(��Ϲ�ư, ������) 
	public JButton calculateRecordButton; 
	public JLabel showingProcess;
	public JPanel upperPanel; 
	public JPanel textPanel;
	
	// �Է�â 
	public JTextField calculatorDisplay;
	public JPanel calculatorDisplayPanel; 
	
	// ����� â
	public JTextArea calculatorRecord;
	public JButton deleteButton;
	public JPanel calculatorRecordPanel; 
	
	private Constants constants;
	
	public TextPanel()
	{
		this.constants = new Constants();

		this.showingProcess = new JLabel();
		showingProcess.setHorizontalAlignment(JLabel.RIGHT);
		this.calculateRecordButton = new JButton("���");
		
		this.upperPanel = new JPanel();
		
		this.calculatorDisplay = new JTextField("0");
		calculatorDisplay.setHorizontalAlignment(JTextField.RIGHT);
		calculatorDisplay.setEditable(false);
		this.calculatorDisplayPanel = new JPanel();
		this.textPanel = new JPanel();
		
		this.calculatorRecord = new JTextArea(50,20);
		calculatorRecord.setEditable(false);
		this.deleteButton = new JButton("����");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculatorRecord .setText("");
			}
		});
		this.calculatorRecordPanel = new JPanel(); 
		
		
		upperPanel.add(calculateRecordButton);
		upperPanel.add(showingProcess);
		upperPanel.setLayout(constants.textGridLayout);
		
		calculatorDisplayPanel.add(calculatorDisplay);
		calculatorDisplayPanel.setLayout(new BorderLayout());
		
		textPanel.add(upperPanel);
		textPanel.add(calculatorDisplayPanel);
		textPanel.setLayout(constants.textGridLayout);
		
		calculatorRecordPanel.add(calculatorRecord);
		calculatorRecordPanel.add(deleteButton);
		
		calculatorDisplayPanel.setLayout(new GridLayout());
		calculateRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JFrame f = new JFrame();
				f.add(calculatorRecordPanel);
				f.setSize(300,100);
				f.setVisible(true);
			}
		}); 
		
		KeyPadPanel keyPadPanel = new KeyPadPanel(showingProcess,calculatorDisplay,calculatorRecord);
		
		add(textPanel);
		add(keyPadPanel);
		
		setLayout(constants.textGridLayout);
	}
}
