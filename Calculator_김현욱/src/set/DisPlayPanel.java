package set;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DisPlayPanel extends JPanel
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
	
	private String lastedOperator; // 가장 최근에 입력된 사칙연산 기호 
	private Double doubleNum;
	private Double doubleSum;
	private boolean isNewNumberStart; // 새로운 숫자가 입력되는 지 판단
	private boolean isEqualNext; // = 를 누른 후 다음 이벤트인지 판단
	private boolean isFirstNumber; // 계산 과정에서 첫번째 입력 숫자인지 확인 
	private boolean isOperatorNext; // 가장 최근에 입력된 게 연산 기호인지 확인 
	
	public DisPlayPanel() {
		setLayout(new BorderLayout());
		
		this.constant = new Constants();
		
		// 계산과정에서 쓰일 변수들 초기화 
		this.doubleNum = 0.0;
		this.doubleSum = 0.0; 
		this.lastedOperator = null;
		this.isNewNumberStart = true;
		this.isFirstNumber = true;
		this.isEqualNext = false;
		this.isOperatorNext = false; 
		
		this.displayPanel = new JPanel(); // 숫자표시칸 관련 패널 
		this.keyPadPanel = new JPanel(); // 숫자패드 관련 패널 
		this.noticePanel = new JPanel();
		noticePanel.setLayout(new GridLayout(0,1));
		this.textArea = new JTextField("0");
		textArea.setBackground(constant.calcGray);
		textArea.setBorder(BorderFactory.createEmptyBorder());
		textArea.setFont(constant.displayFont);
		textArea.setEditable(false);
		this.textNotice = new JLabel();
		this.label = new JLabel();
		label.add(new JButton());
		label.setBackground(constant.calcGray);
		noticePanel.add(label);
		noticePanel.add(textNotice);
		noticePanel.setLayout(new GridLayout(0,1));
		
		textArea.setText("0"); 
		textArea.setHorizontalAlignment(JTextField.RIGHT);
		textArea.setFont(constant.font);
		displayPanel.setLayout(new GridLayout(0,1));
		displayPanel.add(noticePanel);
		displayPanel.add(textArea);
		
		add(displayPanel,BorderLayout.NORTH);
		add(new KeyPad(textArea,label));
	}
}



