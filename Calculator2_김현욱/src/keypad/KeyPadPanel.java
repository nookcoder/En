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
	private boolean isFirstNumberButton;
	private boolean isFirstEqual;
	private boolean isEqualNext;
	private boolean isInfinity;

	private JTextField calculatorDisplay;
	private JLabel showingProcess;
	private JTextArea calculatorRecord;
	private Constants constant;

	public KeyPadPanel(JLabel showingProcess, JTextField calculatorDisplay, JTextArea calculatorRecord) {

		// ������ ȭ�� �ʱ�ȭ
		this.calculatorDisplay = calculatorDisplay;
		this.showingProcess = showingProcess;
		this.calculatorRecord = calculatorRecord;

		// ���� �г� �ʱ�ȭ
		this.keyPadPanel = new JPanel();

		// ������ ���� �ʱ�ȭ
		this.sum = 0.0;
		this.num = 0.0;
		this.temp = 0.0;
		this.isDone = true;
		this.isFirst = true;
		this.isFirstNumberButton = true;
		this.isFirstEqual = true;
		this.isEqualNext = false;
		this.isInfinity = false;

		this.constant = new Constants();

		// ��ư �ʱ�ȭ
		this.numberButton = new JButton[10];
		for (int number = 0; number < 10; number++) {
			numberButton[number] = new JButton(Integer.toString(number));
			constant.setButtonFont(numberButton[number]);
			numberButton[number].addActionListener(new NumberButtonListene());
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

		// ������ �̺�Ʈ �߰�
		plus.addActionListener(new OperatorButton());
		minus.addActionListener(new OperatorButton());
		multiply.addActionListener(new OperatorButton());
		divide.addActionListener(new OperatorButton());
		equal.addActionListener(new OperatorButton());
		c.addActionListener(new ResetButton());
		ce.addActionListener(new ResetButton());
		dot = new JButton(".");
		changingSign = new JButton("��");
		backSpace.addActionListener(new BackSpaceListener());

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
		keyPadPanel.setLayout(new GridLayout(5, 4, 2, 2));

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
		calculatorDisplay.addKeyListener(new CalcKetListener());
		add(keyPadPanel);
		setLayout(new GridLayout());
	}

	// �ι�° ���ڰ� �Էµ��� �� ����
	public void calculate(String operator, Double number) {
		if (operator == "��") {
			sum += number;
		} else if (operator == "��") {
			sum -= number;
		} else if (operator == "��") {
			sum *= number;
		} else if (operator == "��") {
			sum /= number;
		}
	}

	// ���� �ʱ�ȭ �ϱ�
	public void reset() {
		sum = 0.0;
		num = 0.0;
		temp = 0.0;
		isDone = true;
		isFirst = true;
		isFirstNumberButton = true;
		isFirstEqual = true;
		isEqualNext = false;
		operator = null;
		showingProcess.setText("");
		calculatorDisplay.setText("0");
	}

	public void saveCalculatorRecord(String oldSum, Double number) {
		if (operator != null) {
			calculatorRecord.append(oldSum + " " + operator + " " + number.toString() + " = " + sum.toString() + "\n");
		}
	}

	public void actEqual() {
		String oldSum = sum.toString();

		// 0���� ������ ��
		if (isInfinity) {
			reset();
			isDone = false;
			isInfinity = false;
			return;
		}

		// ������ ������ ��ȣ�϶�
		if (isDone) {
			// ��ȣ�� ó�� �Էµ��� ��
			if (isFirstEqual) {
				temp = sum;
				isFirstEqual = false;
			}
			saveCalculatorRecord(oldSum, temp);
			calculate(operator, temp);
			showResult(oldSum, temp);
		}

		// ���� ������ ��ȣ�϶�
		else {
			// �����ڰ� �Է� ���� ��
			if (operator != null) {
				calculate(operator, num);
				showResult(oldSum, num);
				saveCalculatorRecord(oldSum, num);
			}

			// �����ڰ� ���� ��
			else {
				showingProcess.setText(oldSum + " =");
			}
		}
		isEqualNext = true;
	}

	// �Ի�� �α� ȭ�鿡 ǥ�õǰ��ϴ� �Լ�
	public void showResult(String oldSum, Double number) {
		if (sum.toString() == "Infinity") {
			showingProcess.setText("");
			calculatorDisplay.setText("0���� ���� �� �����ϴ�");
			isInfinity = true;
			return;
		}
		showingProcess.setText(oldSum + " " + operator + number.toString() + " =");
		calculatorDisplay.setText(sum.toString());
	}

	// ���� ��ȣ �Է½� ó�� �Լ�
	public void actOperator(String op) {
		String oldSum = sum.toString();

		// �ռ� ������ ����
		if (!isDone && !isEqualNext) {
			calculate(operator, num);
			saveCalculatorRecord(oldSum, num);
			isDone = true;
		}

		operator = op;
		showingProcess.setText(sum.toString() + " " + operator);
		calculatorDisplay.setText(sum.toString());
		isFirst = false;
		isFirstNumberButton = true;
		isFirstEqual = true;
		isEqualNext = false;
	}

	// �齺���̽� ���� 
	public void actBackSpace() {
		if (!isEqualNext) {
			String oldText = calculatorDisplay.getText();
			if (oldText.length() > 1) {
				String newText = oldText.substring(0, oldText.length() - 1);
				calculatorDisplay.setText(newText);
				getNumber();
			}

			else {
				calculatorDisplay.setText("0");
				getNumber();
			}
		}
		else {
			showingProcess.setText("");
		}
	}
	
	public void getNumber() {
		if (isFirst) {
			sum = Double.valueOf(calculatorDisplay.getText());
		} else {
			num = Double.valueOf(calculatorDisplay.getText());
		}
	}

	// ���ڹ�ư �̺�Ʈ������
	private class NumberButtonListene implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			JButton btn = (JButton) e.getSource();
			String number = btn.getText();

			// ��ȣ ���� ���� �Է� �� ���� �ʱ�ȭ
			if (isEqualNext) {
				reset();
				isEqualNext = false;
			}

			// ó�� �Է��ϴ� �������� Ȯ��
			if (isFirstNumberButton || calculatorDisplay.getText() == "0") {
				calculatorDisplay.setText("");
				isFirstNumberButton = false;
			}

			// ���� �Է�
			calculatorDisplay.setText(calculatorDisplay.getText() + number);
			isDone = false;

			// �������� ó�� ���ڸ� sum, ó���̾ƴϸ� num �� ����
			getNumber();
		}
	}

	// �����ư �̺�Ʈ ������
	private class OperatorButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			JButton btn = (JButton) e.getSource();
			String operatorBtn = btn.getText();

			if (operatorBtn == "��") {
				actOperator("��");
			} else if (operatorBtn == "��") {
				actOperator("��");
			} else if (operatorBtn == "��") {
				actOperator("��");
			} else if (operatorBtn == "��") {
				actOperator("��");
			} else if (operatorBtn == "��") {
				actEqual();
			}

		}
	}

	// �ʱ�ȭ ��ư �̺�Ʈ ������( c, ce)
	private class ResetButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String ResetBtn = btn.getText();

			if (ResetBtn == "C") {
				reset();
			}

			else if (ResetBtn == "CE") {
				calculatorDisplay.setText("0");
				if (isFirst) {
					sum = 0.0;
				} else {
					num = 0.0;
				}
				if (isEqualNext) {
					reset();
				}
			}
		}
	}

	
	// �齺���̽� �̺�Ʈ 
	private class BackSpaceListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			actBackSpace();
		}
	}

	// Ű���� �Է� �̺�Ʈ
	private class CalcKetListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			int operatorKeyCode = e.getKeyCode();
			switch (operatorKeyCode) {
			case KeyEvent.VK_ADD:
				actOperator("��");
				break;
			
			case KeyEvent.VK_MINUS:
				actOperator("��");
				break;
			
			case KeyEvent.VK_MULTIPLY:
				actOperator("��");
				break;
			
			case KeyEvent.VK_DIVIDE:
				actOperator("��");
				break;
			
			case KeyEvent.VK_ENTER:
				actEqual();
				break;

			case KeyEvent.VK_EQUALS:
				actEqual();
				break;

			case KeyEvent.VK_ESCAPE:
				reset();
				break;
			
			case KeyEvent.VK_BACK_SPACE:
				actBackSpace();
				break;
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			char number = e.getKeyChar();
			if (number >= '0' && number <= '9') {// ��ȣ ���� ���� �Է� �� ���� �ʱ�ȭ
				if (isEqualNext) {
					reset();
					isEqualNext = false;
				}

				// ó�� �Է��ϴ� �������� Ȯ��
				if (isFirstNumberButton || calculatorDisplay.getText() == "0") {
					calculatorDisplay.setText("");
					isFirstNumberButton = false;
				}

				// ���� �Է�
				calculatorDisplay.setText(calculatorDisplay.getText() + number);
				isDone = false;

				// �������� ó�� ���ڸ� sum, ó���̾ƴϸ� num �� ����
				if (isFirst) {
					sum = Double.valueOf(calculatorDisplay.getText());
				} else {
					num = Double.valueOf(calculatorDisplay.getText());
				}
			} else {
				keyTyped(e);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
