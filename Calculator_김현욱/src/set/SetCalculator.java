package set;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.*;

public class SetCalculator extends JFrame{
	
	static String calculatorText = "0"; 
	
	public SetCalculator() {
		setTitle("°è»ê±â"); 
		setSize(350,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DisPlayPanel settingCalculatorDisplay = new DisPlayPanel();
		add(settingCalculatorDisplay);
		setVisible(true);
	}
}
