package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import model.CmdModel;
import view.CmdView;

public class MoveStatement {
	private CmdView view;
	private CmdController controller;
	private CmdModel model;	
	
	public MoveStatement(CmdView view,CmdController controller,CmdModel model)
	{
		this.view = view;
		this.controller = controller;
		this.model = model;
	}
	
	// copy ��ɹ� ���� 
	public void runMoveStatement(List<String> userStatementList) throws IOException
	{
		if(userStatementList.size() == 3)
		{
			// ������ο� �����Ҷ� 
			if(!userStatementList.get(1).contains(File.separator) && !userStatementList.get(2).contains(File.separator))
			{
				moveFileWithNoPath(userStatementList);
			}
			else if(userStatementList.get(1).contains(File.separator) && !userStatementList.get(2).contains(File.separator))
			{
				moveFileWithNoPath(userStatementList);
			}
			
			else if(userStatementList.get(1).contains(File.separator) && !userStatementList.get(2).contains(File.separator))
			{
				moveFileWithNoPath(userStatementList);
			}
			// ��ΰ� ��� �Էµɶ� 
			else if(userStatementList.get(1).contains(File.separator) && userStatementList.get(2).contains(File.separator))
			{
				moveFileWithNoPath(userStatementList);
			}
		}
		
		else if(userStatementList.size() == 1)
		{
			view.showErrorSyntax();
			view.showRoute(controller.routeName);
		}
	}
	
	
	public void runMove(String originalPath, String movePath) throws IOException
	{
		File originalFile = new File(originalPath);
		File moveFile = new File(movePath);
		// ������ ������ ������ �� 
		if(model.isExistsFile(originalPath))
		{
			model.copyFile(originalFile,moveFile);
			model.deleteFile(originalPath);
			view.showSuccessMove();
			view.showRoute(controller.routeName);
		}
		
		//isDirectory �߰� 
		
		// ���������� ���� �� 
		else
		{
			view.showNoExistsOriginalFile();
			view.showRoute(controller.routeName);
		}
	}
	
	public void moveFileWithNoPath(List<String> userStatementList) throws IOException
	{
		String originalPath = controller.routeName + File.separator+userStatementList.get(1);
		String copyPath = controller.routeName + File.separator+userStatementList.get(2);
		runMove(originalPath,copyPath);
	}
	
	// �̵��ؿ� ���� ��θ� �Ϸµ��� �� 
	public void moveFileWithOriginalPath(List<String> userStatementList) throws IOException
	{
		String originalPath = userStatementList.get(1);
		String copyPath = controller.routeName + File.separator+userStatementList.get(2);
		runMove(originalPath,copyPath);
	}
	
	// �̵��� ���� ��θ� �Էµ��� �� 
	public void moveFileWithCopyPath(List<String> userStatementList) throws IOException
	{
		String originalPath = controller.routeName + File.separator+userStatementList.get(1);
		String copyPath = userStatementList.get(2);
		runMove(originalPath,copyPath);
	}
	
	// ��� �ΰ� �Է� ���� �� 
	public void moveFileWithTwoPath(List<String> userStatementList) throws IOException
	{
		String originalPath = userStatementList.get(1);
		String copyPath = userStatementList.get(2);
		runMove(originalPath,copyPath);
	}
}
