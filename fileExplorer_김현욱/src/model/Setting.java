package model;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.filechooser.FileSystemView;

import images.ImageSource;

public class Setting {
	private ImageSource imgSource;
	
	public Setting(ImageSource imgSource) {
		this.imgSource = imgSource; 
	}
	// ������ ����� 
	public JButton setIcon(File component) {
		
		String filename = component.getName();; 
		ImageIcon img;
		JButton aplication;
		// ���ϸ�� ������ ����� 
		if(component.isDirectory())
		{
			img = imgSource.folderImage; 
		}

		else
		{
			img = setIconimage(component);
		}

		aplication = new JButton(filename,img);
		setButton(aplication);
		aplication.setVerticalTextPosition(JButton.BOTTOM);
		aplication.setHorizontalTextPosition(JButton.CENTER);
		return aplication; 
	}
	
	// ������ �̹��� ����� 
	public ImageIcon setIconimage(File file)
	{
		ImageIcon rowIcon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(file); 
		Image changeIcon = rowIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(changeIcon);
		return icon;
	}

	// ��ư ���� 
	public void setButton(JButton btn)
	{
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
		btn.setPreferredSize(new Dimension(100,100));
	}
}
