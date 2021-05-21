package keypad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import set.Constants;

public class KeyPadPanel extends JPanel {

	private JPanel keyPadPanel;
	
	// ���� �е忡 �� ��ư��
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
	
	// ���������� ���� ���� 
	private Double sum;
	private Double num; 
	private Double temp; 
	private String operator;
	private boolean isDone;
	private boolean isFirst;
	
	private JTextField calculatorDisplay;
	private JLabel showingProcess;
	private Constants constant;
	
	public KeyPadPanel(JLabel showingProcess,JTextField calculatorDisplay) {
		
		// ������ ȭ�� �ʱ�ȭ
		this.calculatorDisplay = calculatorDisplay;
		this.showingProcess = showingProcess;
		
		// ���� �г� �ʱ�ȭ
		this.keyPadPanel = new JPanel();

		// ������ ���� �ʱ�ȭ
		this.sum = 0.0;
		this.num = 0.0; 
		this.temp = 0.0; 
		this.isDone = true;
		this.isFirst = true;
		
		this.constant = new Constants();
		
		// ��ư �ʱ�ȭ 
		this.numberButton = new JButton[10];
		for(int number = 0; number<10;number++)
		{
			numberButton[number] = new JButton(Integer.toString(number));
			constant.setButtonFont(numberButton[number]);
		//	numberButton[number].addActionListener(new NumberButtonListener());
		}
		this.plus = new JButton("��");
		this.minus = new JButton("��");
		this.multiply = new JButton("��");
		this.divide = new JButton("��");
		this.c = new JButton("C");
		this.ce = new JButton("CE");
		this.dot = new JButton(".");
		this.changingSign = new JButton("��");
		this.equal = new JButton("��");
		this.backSpace = new JButton("BACK");
		
		// ��ư �ٹ̱� 
		constant.setButtonFont(plus);
		constant.setButtonFont(minus);
		constant.setButtonFont(multiply);
		constant.setButtonFont(c);
		constant.setButtonFont(ce);
		constant.setButtonFont(dot);
		constant.setButtonFont(changingSign);
		constant.setButtonFont(equal);
		constant.setButtonFont(backSpace);
		constant.setButtonFont(divide);
		
		// ���� �гο� ��ư �߰� 
		keyPadPanel.setLayout(new GridLayout(5,4,2,2));
		
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
		
		add(keyPadPanel);
		setLayout(new GridLayout());
	}
	
	// �ι�° ���ڰ� �Էµ��� �� ���� 
	public void calculateWithNum(String operator)
	{
		if(operator == "��") {sum += num;}
		else if(operator == "��") {sum += num;}
		else if(operator == "��") {sum *= num;}
		else if(operator == "��") {sum /= num;}
	}

	// �����ϳ��� �Էµ��� �� ����
	public void calculateWithNoNum(String operator)
	{
		if(operator == "��") {sum += sum;}
		else if(operator == "��") {sum += sum;}
		else if(operator == "��") {sum *= sum;}
		else if(operator == "��") {sum /= sum;}
	}
	
	// ���ڹ�ư �̺�Ʈ������
	private class NumberButtonListene implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!isDone)
			{
				calculateWithNum(operator);
				isDone = true;
			}
		}
	}
}
