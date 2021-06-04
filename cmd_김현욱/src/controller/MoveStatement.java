package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import model.CmdModel;
import view.CmdView;

public class MoveStatement {
	private CmdView view;
	private CmdController controller;
	private CmdModel model;	
	private CopyStatement copy; 
	
	public MoveStatement(CmdView view,CmdController controller,CmdModel model)
	{
		this.view = view;
		this.controller = controller;
		this.model = model;
	}
	
	// copy ��ɹ� ���� 
	public void runMoveStatement(List<String> userStatementList)
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
	
	
	public void runMove(String originalPath, String movePath)
	{
		// ������ ������ ������ �� 
		if(model.isExistsFile(originalPath))
		{
			try {
				model.copyFile(originalPath,movePath);
				model.deleteFile(originalPath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	public void moveFileWithNoPath(List<String> userStatementList)
	{
		String originalPath = controller.routeName + File.separator+userStatementList.get(1);
		String copyPath = controller.routeName + File.separator+userStatementList.get(2);
		runMove(originalPath,copyPath);
	}
	
	// �̵��ؿ� ���� ��θ� �Ϸµ��� �� 
	public void moveFileWithOriginalPath(List<String> userStatementList)
	{
		String originalPath = userStatementList.get(1);
		String copyPath = controller.routeName + File.separator+userStatementList.get(2);
		runMove(originalPath,copyPath);
	}
	
	// �̵��� ���� ��θ� �Էµ��� �� 
	public void moveFileWithCopyPath(List<String> userStatementList)
	{
		String originalPath = controller.routeName + File.separator+userStatementList.get(1);
		String copyPath = userStatementList.get(2);
		runMove(originalPath,copyPath);
	}
	
	// ��� �ΰ� �Է� ���� �� 
	public void moveFileWithTwoPath(List<String> userStatementList)
	{
		String originalPath = userStatementList.get(1);
		String copyPath = userStatementList.get(2);
		runMove(originalPath,copyPath);
	}
}
