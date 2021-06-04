package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import model.CmdModel;
import view.CmdView;

public class CopyStatement {
	
	private CmdView view;
	private CmdController controller;
	private CmdModel model;	
	
	
	public CopyStatement(CmdView view,CmdController controller, CmdModel model)
	{
		this.view = view;
		this.controller = controller;
		this.model = model;
	}
	
	// copy ��ɹ� ���� 
	public void runCopyStatement(List<String> userStatementList)
	{
		if(userStatementList.size() == 3)
		{
			// ������ο� �����Ҷ� 
			if(!userStatementList.get(1).contains(File.separator) && !userStatementList.get(2).contains(File.separator))
			{
				copyWithNoPath(userStatementList);
			}
			else if(userStatementList.get(1).contains(File.separator) && !userStatementList.get(2).contains(File.separator))
			{
				copyWithOriginalPath(userStatementList);
			}
			
			else if(userStatementList.get(1).contains(File.separator) && !userStatementList.get(2).contains(File.separator))
			{
				copyWithCopyPath(userStatementList);
			}
			// ��ΰ� ��� �Էµɶ� 
			else if(userStatementList.get(1).contains(File.separator) && userStatementList.get(2).contains(File.separator))
			{
				copyWithTwoPath(userStatementList);
			}
		}
		
		else if(userStatementList.size() == 1)
		{
			view.showErrorSyntax();
			view.showRoute(controller.routeName);
		}
	}
	
	// ���� �����ϱ� 
	public void runCopy(String originalPath, String copyPath)
	{
		// ������ ������ ������ �� 
		if(model.isExistsFile(originalPath))
		{
			try {
				model.copyFile(originalPath,copyPath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.showSuccessCopy();
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
	
	// ��ΰ� ���� �� 
	public void copyWithNoPath(List<String> userStatementList)
	{
		String originalPath = controller.routeName + File.separator+userStatementList.get(1);
		String copyPath = controller.routeName + File.separator+userStatementList.get(2);
		runCopy(originalPath,copyPath);
	}
	
	// �����ؿ� ���� ��θ� �Ϸµ��� �� 
	public void copyWithOriginalPath(List<String> userStatementList)
	{
		String originalPath = userStatementList.get(1);
		String copyPath = controller.routeName + File.separator+userStatementList.get(2);
		runCopy(originalPath,copyPath);
	}
	
	// ����� ���� ��θ� �Էµ��� �� 
	public void copyWithCopyPath(List<String> userStatementList)
	{
		String originalPath = controller.routeName + File.separator+userStatementList.get(1);
		String copyPath = userStatementList.get(2);
		runCopy(originalPath,copyPath);
	}
	
	// ��� �ΰ� �Է� ���� �� 
	public void copyWithTwoPath(List<String> userStatementList)
	{
		String originalPath = userStatementList.get(1);
		String copyPath = userStatementList.get(2);
		runCopy(originalPath,copyPath);
	}
//	
//	public String getFirstFilePath(List<String> userStatementList)
//	{
//		String allPath="";
//		int newPathStartIndex;
//		
//		for(int index=1;index < userStatementList.size();index++)
//		{
//			allPath += userStatementList.get(index);
//		}
//		
//		newPathStartIndex = allPath.lastIndexOf(":");
//		
//		return allPath.substring(0, newPathStartIndex-1);
//	}
//	
//	public String getSecondPath(List<String> userStatementList)
//	{
//		String allPath="";
//		int newPathStartIndex;
//		
//		for(int index=1;index < userStatementList.size();index++)
//		{
//			allPath += userStatementList.get(index);
//		}
//		
//		newPathStartIndex = allPath.lastIndexOf(":");
//		
//		return allPath.substring(newPathStartIndex-1, allPath.length());
//	
//	}
}
