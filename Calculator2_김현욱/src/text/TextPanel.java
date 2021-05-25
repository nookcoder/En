package text;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
	public JPanel buttonPanel;
	public JPanel textPanel;
	public JLabel kindLabel;
	
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
		this.kindLabel = new JLabel("ǥ��");
		kindLabel.setFont(constants.ButtonFont);
		this.upperPanel = new JPanel();
		this.buttonPanel = new JPanel();
		
		this.calculatorDisplay = new JTextField("0");
		calculatorDisplay.setHorizontalAlignment(JTextField.RIGHT);
		calculatorDisplay.setEditable(false);
		calculatorDisplay.setBorder(null);
		calculatorDisplay.setFont(constants.calculatorDisplayFont);
		
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
		
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(calculateRecordButton,BorderLayout.EAST);
		buttonPanel.add(kindLabel, BorderLayout.WEST);
		upperPanel.add(buttonPanel);
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
				f.setSize(500,500);
				f.setVisible(true);
			}
		}); 
		
		KeyPadPanel keyPadPanel = new KeyPadPanel(showingProcess,calculatorDisplay,calculatorRecord);
		
		add(textPanel);
		add(keyPadPanel);
		
		setLayout(constants.textGridLayout);	
		
		
	}
}
