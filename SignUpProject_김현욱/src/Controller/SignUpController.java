package Controller;

import java.awt.Color;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;

import com.mysql.jdbc.*;

import model.Constants;
import model.MemberDataBase;
import model.Exception;
import view.SignUp;

public class SignUpController {

	private MemberDataBase member;
	private SignUp signup;
	private Constants constants;
	private Exception exception;
	private boolean isIdCheck = false;
	private boolean isPasswordCheck = false;
	private boolean isNameCheck = false;
	private boolean isBirthCheck = false;
	private boolean isPhoneNumberCheck = false;
	private boolean isEmailCheck = false;
	
	public SignUpController(MemberDataBase member, SignUp signup)
	{
		this.exception= new Exception();
		this.constants = new Constants();
		this.member = member;
		this.signup = signup;
		
		signup.idCheckButton.addActionListener(new IdCheckButtonListener ());
		signup.idField.addKeyListener(new IdFieldListener());
		signup.passwordField.addKeyListener(new PasswordFieldListener());
		signup.passwordCheckField.addActionListener(new PasswordCheckListener());
		signup.nameField.addActionListener(new NameFieldListener());
		signup.birthField.addActionListener(new BirthFieldListener());
		signup.phoneNumberField.addActionListener(new PhoneNumberListenr());
		signup.emailField.addActionListener(new EmailListenr());
		//signup.addressField.addActionListener(null));
		signup.okayButton.addMouseListener(new OkayButtonListener());
		signup.cansleButton.addMouseListener(new CansleButtonListener());
	}
	
	public void setError(String explanation) {
		signup.idField.setText("");
		signup.idExplanation.setForeground(Color.RED);
		signup.idExplanation.setText(explanation);
		signup.idExplanation.setForeground(Color.BLACK);
	}
	
	public void setOkayButtonEnabled() {
		if(isIdCheck && isPasswordCheck && isNameCheck && isBirthCheck && isPhoneNumberCheck && isPhoneNumberCheck)
		{
			signup.okayButton.setEnabled(true);
		}
		
		else
		{
			signup.okayButton.setEnabled(false);
		}
	}
	
	private class IdCheckButtonListener implements ActionListener{

		String IdCheck; 
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			IdCheck = signup.idField.getText();
			if(exception.checkIdInput(IdCheck))
			{
				try {
					if(member.checkIsHaving("id",IdCheck))
					{
						setError("????????? ??????????????????");
						isIdCheck = false;
						return; 
					}
					
					signup.idExplanation.setText("?????? ???????????????!");
					signup.passwordField.requestFocus();
					isIdCheck = true;
					setOkayButtonEnabled();
					return;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			isIdCheck = false;
			setError("?????????????????? ???????????????");
		}
	}
	
	private class IdFieldListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			isIdCheck = false; 
			signup.idExplanation.setText("4~12?????? ?????? ?????????,????????? ?????????????????????");
			if((e.getKeyCode() == KeyEvent.VK_TAB||e.getKeyCode() == KeyEvent.VK_ENTER) && !isIdCheck)
			{
				signup.idExplanation.setText("?????? ????????? ????????????!");	
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class PasswordFieldListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			String password = signup.getPassword();
			
			if(e.getKeyCode() == KeyEvent.VK_TAB ||e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if(exception.checkPasswordInput(password))
				{
					signup.passwordCheckField.requestFocus();
					signup.passwordExplanation.setText("?????????????????????");
					isPasswordCheck = true;
					setOkayButtonEnabled();
					return;
				}
				isPasswordCheck = false;
				signup.passwordField.setText("");
				signup.passwordExplanation.setText("?????????????????? ???????????????");
				return;
			}
			
			checkPassword(password);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		public void checkPassword(String password) {
			if(exception.checkPasswordInput(password))
			{
				isPasswordCheck = true;
				signup.passwordExplanation.setText("?????????????????????");
			}
			
			else
			{
				isPasswordCheck = false;
				signup.passwordExplanation.setText("8~16??? ?????? ??? ?????????, ????????? ???????????????");
			}
		}
	}

	private class PasswordCheckListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String passwordCheck = signup.getPasswordCheck();
			String password = signup.getPassword();
			if(passwordCheck.equals(password))
			{
				isPasswordCheck = true;
				setOkayButtonEnabled();
				signup.nameField.requestFocus();
				signup.passwordCheckExplanation.setText("???????????????");
			}
			
			else
			{
				isPasswordCheck = false;
				signup.passwordCheckField.setText("");
				signup.passwordCheckExplanation.setText("??????????????? ??????????????????");
			}
		}
		

		
	}

	private class NameFieldListener implements ActionListener{

		String name; 

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			name = signup.getName();
			if(exception.checkNameInput(name))
			{
				isNameCheck = true;
				setOkayButtonEnabled();
				signup.nameExplanation.setText(name + "??? ???????????????");
				signup.birthField.requestFocus();
				
			}
			else {
				isNameCheck = true;
				signup.nameExplanation.setText("????????? ???????????????");
				signup.nameField.setText("");
			}	
		}
		
	}

	private class BirthFieldListener implements ActionListener{

		String birth; 
		@Override
		public void actionPerformed(ActionEvent e) {

			birth = signup.getBirth();
			// TODO Auto-generated method stub
			if(exception.checkBirthInput(birth))
			{
				isBirthCheck = true;
				setOkayButtonEnabled();
				signup.birthExplanation.setText("??????????????????.");
				signup.phoneNumberField.requestFocus();
			}
			else {
				isBirthCheck = false;
				signup.birthExplanation.setText("????????? ???????????????");
				signup.birthField.setText("");
			}
		}
		
	}

	private class PhoneNumberListenr implements ActionListener{

		String phoneNumber;
		@Override
		public void actionPerformed(ActionEvent e) {
			phoneNumber = signup.getPhoneNumber();
			// TODO Auto-generated method stub
			if(exception.checkPhoneNumberInput(phoneNumber))
			{
				try {
					if(member.checkIsHaving("phoneNumber",phoneNumber))
					{
						signup.phoneNumberExplanation.setText("????????? ???????????????");
						signup.phoneNumberField.setText("");
						isPhoneNumberCheck = false;
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				signup.phoneNumberExplanation.setText("??????????????????");
				isPhoneNumberCheck = true;
				setOkayButtonEnabled();
				signup.emailField.requestFocus();
			}
			
			else {
				signup.phoneNumberField.setText("");
				signup.phoneNumberExplanation.setText("????????? ???????????????");	
				isPhoneNumberCheck = false;
			}
		}
		
	}
	
	private class EmailListenr implements ActionListener{

		String email;
		@Override
		public void actionPerformed(ActionEvent e) {
			email = signup.getEmail();
			// TODO Auto-generated method stub
			if(exception.checkEmailInput(email))
			{
				try {
					if(member.checkIsHaving("email", email))
					{
						signup.emailExplanation.setText("????????? ??????????????????");
						signup.emailField.setText("");
						isEmailCheck = false;
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				signup.emailExplanation.setText("??????????????????");
				isEmailCheck = true;
				setOkayButtonEnabled();
				signup.addressField.requestFocus();
			}
			
			else {
				signup.emailField.setText("");
				signup.emailExplanation.setText("????????? ???????????????");	
				isEmailCheck = false;
			}
		}
		
	}

	//private class addressListener 
	
	private class OkayButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(isIdCheck && isPasswordCheck && isNameCheck && isBirthCheck && isPhoneNumberCheck && isPhoneNumberCheck)
			{
				String id = signup.getId();
				String password = signup.getPassword();
				String name = signup.getName();
				String birth = signup.getBirth();
				String email = signup.getEmail();
				String phoneNumber = signup.getPhoneNumber();
				String address = signup.getAddress();
				try {
					member.insertData(id,password,name,birth,email,phoneNumber,address);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				signup.setVisible(false);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getComponent().isEnabled())
			{
				e.getComponent().setBackground(e.getComponent().getBackground().darker());
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getComponent().isEnabled())
			{
				e.getComponent().setBackground(e.getComponent().getBackground().brighter());
			}
		}
	}
	
	private class CansleButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			signup.setVisible(false);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			e.getComponent().setBackground(e.getComponent().getBackground().darker());
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			e.getComponent().setBackground(e.getComponent().getBackground().brighter());
		}
		
	}
}
