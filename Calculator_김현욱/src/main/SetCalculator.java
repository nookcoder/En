package main;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;

public class SetCalculator extends JFrame{
	
	static String calculatorText = "0"; 
	
	public SetCalculator() {
		setTitle("����"); 
		setSize(350,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SettingCalculatorDisplay settingCalculatorDisplay = new SettingCalculatorDisplay();
		add(settingCalculatorDisplay);
		setVisible(true);
	}
}
