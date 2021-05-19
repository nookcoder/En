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
		this.textArea = new JTextField("0");
		this.textNotice = new JLabel();
		this.label = new JLabel();
		label.add(new JButton());
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
		add(new KeyPad(textArea));
	}
}



