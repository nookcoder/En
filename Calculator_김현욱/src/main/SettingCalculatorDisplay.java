package main;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingCalculatorDisplay extends JPanel
{
	private JPanel displayPanel;
	private JPanel keyPadPanel;
	private JTextField textArea; 
	private JPanel noticePanel;
	private JLabel label;
	private JLabel textNotice;
	private JButton[] numberButton;
	private JButton ce; 
	private JButton c; 
	private JButton backSpace;
	private JButton divide;
	private JButton plus;
	private JButton equal;
	private JButton minus;
	private JButton multiply;
	private JButton dot;
	private JButton changingSign;
	
	private Constants constant;
	
	private String lastedOperator; // ���� �ֱٿ� �Էµ� ��Ģ���� ��ȣ 
	private Double doubleNum;
	private Double doubleSum;
	private boolean isNewNumberStart; // ���ο� ���ڰ� �ԷµǴ� �� �Ǵ�
	private boolean isEqualNext; // = �� ���� �� ���� �̺�Ʈ���� �Ǵ�
	private boolean isFirstNumber; // ��� �������� ù��° �Է� �������� Ȯ�� 
	private boolean isOperatorNext; // ���� �ֱٿ� �Էµ� �� ���� ��ȣ���� Ȯ�� 
	
	public SettingCalculatorDisplay() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		this.constant = new Constants();
		
		// ���������� ���� ������ �ʱ�ȭ 
		this.doubleNum = 0.0;
		this.doubleSum = 0.0; 
		this.lastedOperator = null;
		this.isNewNumberStart = true;
		this.isFirstNumber = true;
		this.isEqualNext = false;
		this.isOperatorNext = false; 
		
		this.displayPanel = new JPanel(); // ����ǥ��ĭ ���� �г� 
		this.keyPadPanel = new JPanel(); // �����е� ���� �г� 
		this.noticePanel = new JPanel();
		noticePanel.setLayout(new GridLayout(0,1));
		
		this.keyPadPanel = new JPanel(); 
		this.numberButton = new JButton[10];
		
		// ���� ��ư ���� 
		for(int number = 0; number<10;number++)
		{
			numberButton[number] = new JButton(Integer.toString(number));
			setButtonFont(numberButton[number]);
			
			numberButton[number].addActionListener(new NumberButtonListener());
		}
		
		this.plus = new JButton("+");
		this.minus = new JButton("��");
		this.multiply = new JButton("X");
		this.divide = new JButton("/");
		this.c = new JButton("C");
		
		this.ce = new JButton("CE");
		this.dot = new JButton(".");
		this.changingSign = new JButton("+/-");
		this.equal = new JButton("=");
		this.backSpace = new JButton("BACK");
	
		// �����ȣ ��ư Ŀ����
		setSignButtonFont(plus);
		setSignButtonFont(minus);
		setSignButtonFont(multiply);
		setSignButtonFont(c);
		setSignButtonFont(ce);
		setButtonFont(dot);
		setButtonFont(changingSign);
		setSignButtonFont(equal);
		setSignButtonFont(backSpace);
		setSignButtonFont(divide);
		
		// ���� ��ȣ �̺�Ʈ ó�� 
		plus.addActionListener(new OperatorListener());
		minus.addActionListener(new OperatorListener());
		multiply.addActionListener(new OperatorListener());
		divide.addActionListener(new OperatorListener());
		equal.addActionListener(new EqualListener());
		c.addActionListener(new ResetListener());
		ce.addActionListener(new ResetListener()); 
		dot.addActionListener(new DotListener());
		changingSign.addActionListener(new ChangingSignListener());
		backSpace.addActionListener(null);

		// �����е� �����
		keyPadPanel.add(ce);
		keyPadPanel.add(c);
		keyPadPanel.add(backSpace);
		keyPadPanel.add(divide);
		
		keyPadPanel.add(numberButton[7]);
		keyPadPanel.add(numberButton[8]);
		keyPadPanel.add(numberButton[9]);
		keyPadPanel.add(multiply);
		
		keyPadPanel.add(numberButton[4]);
		keyPadPanel.add(numberButton[5]);
		keyPadPanel.add(numberButton[6]);
		keyPadPanel.add(minus);
		
		keyPadPanel.add(numberButton[1]);
		keyPadPanel.add(numberButton[2]);
		keyPadPanel.add(numberButton[3]);
		keyPadPanel.add(plus);
		
		keyPadPanel.add(changingSign);
		keyPadPanel.add(numberButton[0]);
		keyPadPanel.add(dot);
		keyPadPanel.add(equal);
		
		keyPadPanel.setLayout(new GridLayout(5,4,2,2));
		
		this.textArea = new JTextField("0");
		this.textNotice = new JLabel("1");
		this.label = new JLabel();
		label.add(new JButton());
		//label.setHorizontalAlignment(JLabel.RIGHT);
		noticePanel.add(label);
		noticePanel.add(textNotice);
		noticePanel.setLayout(new GridLayout(0,1));
		
		textArea.setText("0"); 
		textArea.setHorizontalAlignment(JTextField.RIGHT);
		textArea.setFont(constant.font);
		displayPanel.setLayout(new GridLayout(0,1));
		displayPanel.add(noticePanel);
		displayPanel.add(textArea);
		add(displayPanel);
		add(keyPadPanel);
		}		
	
	public void setButtonFont(JButton btn) {
		btn.setFont(constant.font);
		btn.setBorderPainted(false);
		btn.setBackground(Color.WHITE);
	}
	
	public void setSignButtonFont(JButton btn) {
		btn.setFont(constant.signFont);
		btn.setBorderPainted(false);
		btn.setBackground(Color.LIGHT_GRAY);
	}
	
	public void runOperation(String operator)
	{
			if(operator == "+") {doubleSum += doubleNum;}
			else if(operator == "��") {doubleSum -= doubleNum;}
			else if(operator == "X") {doubleSum *= doubleNum;}
			else if(operator == "/") {doubleSum /= doubleNum;}
	}
	
	public void setPrintingNumberToInt(Double number)
	{
		String[] numberString = number.toString().split(".");
		if(numberString[1].equals("0")) {
			textArea.setText(numberString[0]);
		}
		else {
			textArea.setText(number.toString());
		}
		
	}
	
	public void reset() {
		isNewNumberStart  = true; 
		isFirstNumber = true;
		isEqualNext = false;
		isOperatorNext = false;
		lastedOperator = null;
		
		doubleNum = 0.0;
		doubleSum = 0.0;
	}
	
	// ���� Ű �Է� �� �̺�Ʈ ó�� 
	private class NumberButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			JButton numberButton = (JButton)e.getSource();
			String number = numberButton.getText();
			String result = textArea.getText(); 
			
			if(isEqualNext)
			{
				reset();
				textArea.setText(number);
			}
			
			// ���ο� ���� �Է½� ���� �������ִ� ���� ����� 
			if(isNewNumberStart ||result.equals("0")||isEqualNext)
			{
				textArea.setText("");
				textArea.setText(number);
			}					
			// �������� ���� �Է� �� 
			else {textArea.setText(result + number);}
			
			getNumber();
			
			// ���� ��ȣ ���� ������ �� ó��(������ִ� ����) 
			isOperatorNext = false;
			isNewNumberStart = false;
			isEqualNext = false;
		}
		
		public void getNumber() {
			// �Է��� ���� ���� 
			if(isFirstNumber) {doubleSum = Double.valueOf(textArea.getText());}
			else{ doubleNum = Double.valueOf(textArea.getText());}
		}
	}
	
	// ���� ��ȣ �̺�Ʈ ó�� 
	private class OperatorListener implements ActionListener{
		public void actionPerformed(ActionEvent e ) {
			
			JButton operatorButton = (JButton)e.getSource();
			String operator = operatorButton.getText();
			
			if(lastedOperator  != null && !isEqualNext) {runOperation(lastedOperator);}

			lastedOperator = operator;	
			
			isOperatorNext = true;
			isNewNumberStart  = true;
			isEqualNext = false;
			isFirstNumber = false;
		}		
	}
	
	// ��ȣ(=) �̺�Ʈ ó�� 
	private class EqualListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			if(!isOperatorNext)
			{
				isEqualNext = true;
				runOperation(lastedOperator);
				String doubleSumString = Double.toString(doubleSum);
				String doubleSumCheck = doubleSumString.substring(doubleSumString.length() - 1);
				
				if(doubleSumCheck.equals("0"))
				{
					textArea.setText(doubleSum.toString().substring(0, doubleSumString.length()-2));
				}
				
				else
				{
					textArea.setText(doubleSum.toString());
				}
			}
		}
	}
	
	// c ce �Է½� �̺�Ʈ ó�� 
	private class ResetListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e)
		{
			JButton resetButton = (JButton)e.getSource();
			
			// ���� �ʱ�ȭ 
			if(resetButton.getText() == "C")
			{
				textArea.setText("0");
				reset();
			}
			
			// ��ȣ ���� ce �Է� �� �ʱ�ȭ, �������� ce�Է½� ���ڸ� �ʱ�ȭ 
			else if (resetButton.getText() == "CE")
			{
				textArea.setText("0");
				if(isEqualNext) {
					reset();
				}
			}
		}
	}

	// +/- �Է� �̺�Ʈ ó�� 
	private class ChangingSignListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e)
		{ 
			// ���⿡�� ��ó������ �Է��� �� 
			if(isNewNumberStart)
			{
				textArea.setText("");
				textArea.setText("-");
				isNewNumberStart = false;
				if(!textArea.getText().equals("-")) { doubleSum = Double.valueOf(textArea.getText());}
				return;
			}
			
			if(isFirstNumber) 
			{
				showChangeOperatorWithDouble(doubleSum);
				doubleSum = Double.valueOf(textArea.getText());
			}
			else
			{
				showChangeOperatorWithDouble(doubleNum);
				doubleNum = Double.valueOf(textArea.getText());
			}
		}
		
		// ��ȣ �ٲ�� �� ����ڿ��� �����ִ� �Լ� (�Ǽ�)
		public void showChangeOperatorWithDouble(Double number) {
			
			if(number > 0)
			{
				textArea.setText("");
				textArea.setText("-" + number.toString());
			}
			
			else if(number < 0)
			{
				textArea.setText("");
				textArea.setText(number.toString());
			}
		}
	}
	
	private class DotListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String oldString = textArea.getText();
			String newString = oldString + ".";
			
			if(!textArea.getText().contains(".")) {textArea.setText(newString);}
		}
		
	}
}



