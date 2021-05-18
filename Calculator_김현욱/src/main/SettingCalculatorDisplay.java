package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingCalculatorDisplay extends JPanel
{
	private JPanel displayPanel;
	private JPanel keyPadPanel;
	
	static JTextField textArea; 
	
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
	private int num; // �Էµ� ���� 
	private int sum; // ��µ� ��� ��� ��
	private boolean isNewNumberStart  = true; // ���ο� ���ڰ� �ԷµǴ� �� �Ǵ�
	private boolean isEqualNext = false; // = �� ���� �� ���� �̺�Ʈ���� �Ǵ�
	private boolean isFirstNumber = true; // ��� �������� ù��° �Է� �������� Ȯ�� 
	private boolean isOperatorNext = false; // ���� �ֱٿ� �Էµ� �� ���� ��ȣ���� Ȯ�� 
	private boolean isChanging = false; 
	
	public SettingCalculatorDisplay() {
		this.constant = new Constants();
		
		// ���������� ���� ������ �ʱ�ȭ 
		this.num = 0; 
		this.sum = 0;
		this.lastedOperator = null;
		this.isNewNumberStart = true;
		this.isFirstNumber = true;
		this.isEqualNext = false;
		this.isOperatorNext = false; 
		
		this.displayPanel = new JPanel(); // ����ǥ��ĭ ���� �г� 
		this.keyPadPanel = new JPanel(); // �����е� ���� �г� 
		
		displayPanel.setLayout(new FlowLayout());
		keyPadPanel.setLayout(new GridLayout(0,4,5,5));
		
		this.textArea = new JTextField(11);
		textArea.setText("0"); 
		textArea.setHorizontalAlignment(JTextField.RIGHT);
		textArea.setFont(constant.font);
		
		add(textArea,BorderLayout.NORTH);
		
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
		setSignButtonFont(dot);
		setSignButtonFont(changingSign);
		setSignButtonFont(equal);
		setSignButtonFont(backSpace);

		// ���� ��ȣ �̺�Ʈ ó�� 
		plus.addActionListener(new OperatorListener());
		minus.addActionListener(new OperatorListener());
		multiply.addActionListener(new OperatorListener());
		divide.addActionListener(new OperatorListener());
		equal.addActionListener(new EqualListener());
		c.addActionListener(new ResetListener());
		ce.addActionListener(new ResetListener()); 
		dot.addActionListener(null);
		changingSign.addActionListener(new ChangingSignListener());
		backSpace.addActionListener(null);

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
		
		keyPadPanel.setLayout(new GridLayout(5,4));
		
		add(keyPadPanel,BorderLayout.CENTER);
		}		
	
	
	public void setButtonFont(JButton btn) {
		btn.setFont(constant.font);
	}
	
	public void setSignButtonFont(JButton btn) {
		btn.setFont(constant.signFont);
	}
	
	public void runOperation(String operator)
	{
		if(operator == "+") {sum += num;}
		else if(operator == "��") {sum -= num;}
		else if(operator == "X") {sum *= num;}
		else if(operator == "/") {sum /= num;}
	}

	public void reset() {
		isNewNumberStart  = true; 
		isFirstNumber = true;
		isEqualNext = false;
		isOperatorNext = false;
		lastedOperator = null;
		isChanging = false;
		
		num = 0; 
		sum = 0;
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
			
			// �Է��� ���� ���� 
			if(isFirstNumber) {sum = Integer.parseInt(textArea.getText());}
			else{ num = Integer.parseInt(textArea.getText());}

			// ���� ��ȣ ���� ������ �� ó��(������ִ� ����) 
			
			isNewNumberStart = false;
			isEqualNext = false;
			isOperatorNext = false;
		}
	}
	
	// ���� ��ȣ �̺�Ʈ ó�� 
	private class OperatorListener implements ActionListener{
		public void actionPerformed(ActionEvent e ) {
			
			JButton operatorButton = (JButton)e.getSource();
			String operator = operatorButton.getText();
			
			if(lastedOperator  != null) {runOperation(lastedOperator);}

			lastedOperator = operator;	
			
			isOperatorNext = true;
			isChanging = false;
			isNewNumberStart  = true;
			isFirstNumber = false;
		}		
	}
	
	// ��ȣ(=) �̺�Ʈ ó�� 
	private class EqualListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			if(!isOperatorNext)
			{
	
				if(isChanging)
				{
					sum *= -1;
					isEqualNext = true;
					runOperation(lastedOperator);
					textArea.setText(Integer.toString(sum));
				}
				
				else if(!isChanging)
				{
					isEqualNext = true;
					runOperation(lastedOperator);
					textArea.setText(Integer.toString(sum));
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

	private class ChangingSignListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e)
		{ 
			// ���⿡�� ��ó������ �Է��� �� 
			if(isNewNumberStart)
			{
				textArea.setText("");
				textArea.setText("-");
				isNewNumberStart = false;
				setChangingSign();
				return;
			}
	
			ShowChangeOperator(sum);
			setChangingSign();
		}
		
		public void setChangingSign() {
			if(!textArea.getText().equals("-")) {sum = Integer.parseInt(textArea.getText());}
			isChanging = true;
		}
	}
	
	// ��ȣ �ٲ�� �� ����ڿ��� �����ִ� �Լ� 
	public void ShowChangeOperator(int number) {
		
		if(number > 0)
		{
			textArea.setText("");
			textArea.setText("-" + Integer.toString(number));
		}
		
		else if(number < 0)
		{
			textArea.setText("");
			textArea.setText(Integer.toString(number));
		}
	}
	
}



