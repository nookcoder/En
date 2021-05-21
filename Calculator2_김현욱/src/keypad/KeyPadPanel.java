package keypad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import set.Constants;

public class KeyPadPanel extends JPanel {

	private JPanel keyPadPanel;

	// 숫자 패드에 들어갈 버튼들
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

	// 계산과정에서 쓰일 변수
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

	public KeyPadPanel(JLabel showingProcess, JTextField calculatorDisplay,JTextArea calculatorRecord) {

		// 계산과정 화면 초기화
		this.calculatorDisplay = calculatorDisplay;
		this.showingProcess = showingProcess;
		this.calculatorRecord = calculatorRecord;
		
		// 숫자 패널 초기화
		this.keyPadPanel = new JPanel();

		// 계산과정 변수 초기화
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

		// 버튼 초기화
		this.numberButton = new JButton[10];
		for (int number = 0; number < 10; number++) {
			numberButton[number] = new JButton(Integer.toString(number));
			constant.setButtonFont(numberButton[number]);
			numberButton[number].addActionListener(new NumberButtonListene());
		}
		this.plus = new JButton("＋");
		this.minus = new JButton("－");
		this.multiply = new JButton("×");
		this.divide = new JButton("÷");
		this.c = new JButton("C");
		this.ce = new JButton("CE");
		this.dot = new JButton(".");
		this.changingSign = new JButton("±");
		this.equal = new JButton("＝");
		this.backSpace = new JButton("BACK");

		// 연산자 이벤트 추가
		plus.addActionListener(new OperatorButton());
		minus.addActionListener(new OperatorButton());
		multiply.addActionListener(new OperatorButton());
		divide.addActionListener(new OperatorButton());
		equal.addActionListener(new OperatorButton());
		c.addActionListener(new ResetButton());
		ce.addActionListener(new ResetButton());
		dot = new JButton(".");
		changingSign = new JButton("±");
		backSpace = new JButton("BACK");

		// 버튼 꾸미기
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

		// 숫자 패널에 버튼 추가
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

		add(keyPadPanel);
		setLayout(new GridLayout());
	}

	// 두번째 숫자가 입력됐을 때 연산
	public void calculateWithNum(String operator) {
		if (operator == "＋") {
			sum += num;
		} else if (operator == "－") {
			sum -= num;
		} else if (operator == "×") {
			sum *= num;
		} else if (operator == "÷") {
			sum /= num;
		}
	}

	// 숫자하나만 입력됐을 때 연산
	public void calculateWithNoNum(String operator) {
		if (operator == "＋") {
			sum += temp;
		} else if (operator == "－") {
			sum -= temp;
		} else if (operator == "×") {
			sum *= temp;
		} else if (operator == "÷") {
			sum /= temp;
		}
	}

	// 계산기 초기화 하기
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

	// 숫자버튼 이벤트리스너
	private class NumberButtonListene implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			JButton btn = (JButton) e.getSource();
			String number = btn.getText();

			// 등호 다음 숫자 입력 시 계산기 초기화
			if (isEqualNext) {
				reset();
				isEqualNext = false;
			}

			// 처음 입력하는 숫자인지 확인
			if (isFirstNumberButton || calculatorDisplay.getText() =="0") {
				calculatorDisplay.setText("");
				isFirstNumberButton = false;
			}

			// 숫자 입력
			calculatorDisplay.setText(calculatorDisplay.getText() + number);
			isDone = false;
			
			// 계산과정의 처음 숫자면 sum, 처음이아니면 num 에 저장
			if (isFirst) {
				sum = Double.valueOf(calculatorDisplay.getText());
			} else {
				num = Double.valueOf(calculatorDisplay.getText());
			}
		}
	}

	// 연산버튼 이벤트 리스너 
	private class OperatorButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			JButton btn = (JButton) e.getSource();
			String operatorBtn = btn.getText();

			if (operatorBtn == "＋") {
				actOperator("＋");
			} else if (operatorBtn == "－") {
				actOperator("－");
			} else if (operatorBtn == "×") {
				actOperator("×");
			} else if (operatorBtn == "÷") {
				actOperator("÷");
			} else if (operatorBtn == "＝") {
				actEqual();
			}

		}
		
		// 등호 입력 시 실행 함수 
		public void actEqual() {
			String oldSum = sum.toString();
			
			// 0으로 나눴을 때 
			if(isInfinity)
			{
				reset();
				isDone=false;
				isInfinity = false;
				return;
			}
			
			// 연산자 다음의 등호일때 
			if (isDone) 
			{
				// 등호가 처음 입력됐을 때 
				if (isFirstEqual) 
				{
					temp = sum;
					isFirstEqual = false;
				}
				//calculatorRecord.append(oldSum + " "+operator+" "+temp.toString()+" = "+sum.toString());
				calculateWithNoNum(operator);
				showResult(oldSum);
			} 
			
			// 숫자 다음의 등호일때 
			else
			{
				// 연산자가 입력 됐을 때 
				if (operator != null) 
				{
					calculateWithNum(operator);
					showResult(oldSum);
					calculatorRecord.append(oldSum + " "+operator+" "+num.toString()+" = "+sum.toString());
				}
				
				// 연산자가 없을 떄 
				else
				{
					showingProcess.setText(oldSum + " =");
				}
			}
			isEqualNext = true;
		}
		
		// 게산기 로그 화면에 표시되게하는 함수 
		public void showResult(String oldSum) {
			if(sum.toString() == "Infinity") 
			{
				showingProcess.setText("");
				calculatorDisplay.setText("0으로 나눌 수 없습니다");
				isInfinity = true;
				return;
			}
			showingProcess.setText(oldSum + " " + operator + num.toString() + " =");
			calculatorDisplay.setText(sum.toString());
		}

		// 연산 기호 입력시 처리 함수 
		public void actOperator(String op) {
			String oldSum = sum.toString();
			
			// 앞선 연산자 적용
			if (!isDone && !isEqualNext) {
				calculateWithNum(operator);
				if(operator != null)
				{
					calculatorRecord.append(oldSum + " "+operator+" "+num.toString()+" = "+sum.toString());
				}
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
	}

	// 초기화 버튼 이벤트 리스너( c, ce) 
	private class ResetButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String ResetBtn = btn.getText();
			
			if(ResetBtn == "C")
			{
				reset();
			}
			
			else if(ResetBtn == "CE")
			{
				calculatorDisplay.setText("0");
				if(isFirst) {sum = 0.0;}
				else {num = 0.0;}
				if(isEqualNext)
				{
					reset();
				}
			}
		}
	}
}
