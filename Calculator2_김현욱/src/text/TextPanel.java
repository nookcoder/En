package text;

import java.awt.GridLayout;
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
	
	private Constants constants;
	
	public TextPanel()
	{
		this.constants = new Constants();

		this.showingProcess = new JLabel("11");
		showingProcess.setHorizontalAlignment(JLabel.RIGHT);
		this.calculateRecordButton = new JButton("���");
		this.upperPanel = new JPanel();
		
		this.calculatorDisplay = new JTextField("0");
		calculatorDisplay.setHorizontalAlignment(JTextField.RIGHT);
		this.calculatorDisplayPanel = new JPanel();
		this.textPanel = new JPanel();
		
		upperPanel.add(calculateRecordButton);
		upperPanel.add(showingProcess);
		upperPanel.setLayout(constants.textGridLayout);
		
		calculatorDisplayPanel.add(calculatorDisplay);
		calculatorDisplayPanel.setLayout(new GridLayout());
		
		textPanel.add(upperPanel);
		textPanel.add(calculatorDisplayPanel);
		textPanel.setLayout(constants.textGridLayout);
		
		KeyPadPanel keyPadPanel = new KeyPadPanel(showingProcess,calculatorDisplay);
		
		add(textPanel);
		add(keyPadPanel);
		
		setLayout(constants.textGridLayout);
	}
}
