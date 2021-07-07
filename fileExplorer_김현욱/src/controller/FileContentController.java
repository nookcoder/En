package controller;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import images.ImageSource;
import model.Setting;
import view.Alert;
import view.FileContentView;
import view.FileTreeView;
import view.PathView;

public class FileContentController {
	
	private FileTreeView treeView; 
	private FileContentView contentView; 
	private PathView pathView; 
	private ImageSource imgSource = new ImageSource();
	private Setting set; 
	
	public Stack<File> backLog = new Stack<>();
	public Stack<File> forwardLog = new Stack<>();
	public JButton clickedButton = new JButton(); 
	
	public FileContentController(FileTreeView treeView,FileContentView contentView,PathView pathView)
	{
		this.treeView = treeView;
		this.contentView = contentView; 
		this.pathView = pathView;
		
		this.set = new Setting(imgSource);
		
		treeView.tree.addTreeSelectionListener(new TreeSelectionListener() {
			// Ʈ����� Ŭ���ϸ� ������ ����
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				File component = new File(pathView.pathField.getText());
				backLog.push(component);
				addIcon(component);
			}
		});
		
		pathView.pathField.addActionListener(new SearchListener());
	}
	
	// ������ �гο� ������ �迭�ϱ� 
	public void addIcon(File component)
	{
		File[] components = component.listFiles();
		contentView.iconPanel.removeAll();
		if(components == null)
		{
			contentView.iconPanel.revalidate();
			contentView.iconPanel.repaint();
			contentView.iconPanel.updateUI();
			return;
		}
		
		for(File child : components)
		{
			if(!child.isHidden() && Files.isReadable(child.toPath()))
			{
				JButton icon = set.setIcon(child);
				icon.addMouseListener(new iconMouseListener());
				contentView.iconPanel.add(icon);
			}		
		}
		contentView.iconPanel.revalidate();
		contentView.iconPanel.repaint();
		contentView.iconPanel.updateUI();
	}
	
	// �˻���� ���� ã�� 
	public void findFile(File file) 
	{
		String keyword = pathView.searchField.getText();
		File[] fileList = file.listFiles();
		// ������ ��������� return 
		if(fileList == null)
		{
			return;
		}
		
		// �˻�� ���Ե� ���� ã�� 
		for(File target : fileList)
		{
			if(target.isHidden() || !Files.isReadable(target.toPath()))
			{
				return; 
			}
			
			if(target.getName().contains(keyword))
			{
				JButton icon = set.setIcon(target);
				icon.addMouseListener(new iconMouseListener());
				contentView.iconPanel.add(icon);
			}
			
			if(target.isDirectory())
			{
				findFile(target);
			}
		}
	}
	
	// ���â �˻� �̺�Ʈ
	private class SearchListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String currentPath = pathView.pathField.getText().toLowerCase();
			File searchFile = new File(currentPath);
			if(!searchFile.exists())
			{
				Alert alert = new Alert();
				alert.setMessageText(currentPath);
				return;
			}
			addIcon(searchFile);
			backLog.push(searchFile);
			try {
				pathView.pathField.setText(searchFile.getCanonicalPath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	// ���콺 �̺�Ʈ������ 
	private class iconMouseListener implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			JButton btn = (JButton) e.getSource();

			// ���� �� ���� �̵� ����
			if(e.getClickCount() == 2)
			{
				File file = new File(pathView.pathField.getText() +File.separator+ btn.getText());
				backLog.push(file);
				
				if(file.isDirectory())
				{
					addIcon(file);
					pathView.pathField.setText(pathView.pathField.getText().toString() +File.separator+ btn.getText());
					return;
				}

				try {
					Desktop.getDesktop().open(file);
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// ������ Ŭ�����ִ� ��ư ���� ���ֱ� 
			if(clickedButton.isBackgroundSet())
			{
				clickedButton.setContentAreaFilled(false);
			}
			
			// ���ο� ��ư�� ���� ������
			JButton btn = (JButton) e.getSource();
			clickedButton = btn;
			btn.setContentAreaFilled(true);
			btn.setBackground(new Color(170,226,247));
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton btn = (JButton) e.getSource();
			if(!btn.isFocusOwner())
			{
				btn.setContentAreaFilled(true);
				btn.setBackground(new Color(195,226,247));
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton btn = (JButton) e.getSource();
			if(!btn.isFocusOwner())
			{
				btn.setContentAreaFilled(false);
			}
		}
	}

}
