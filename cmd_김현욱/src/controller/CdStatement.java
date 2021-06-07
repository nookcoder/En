package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cmd_������.Constants;
import model.CmdModel;
import view.CmdView;

public class CdStatement {

	private CmdController controller;
	private CmdModel model;
	private CmdView view;
	private Constants constants;
	
	public CdStatement(CmdView view, CmdController controller,CmdModel model,Constants constants)
	{
		this.view = view;
		this.controller = controller;
		this.model = model;
		this.constants = new Constants();
	}

	// cd ��ɹ� ���� 
	public void runCdStatement(List<String> userStatementList) throws IOException
	{
		// cd�� �Էµ����� 
		if(userStatementList.get(0).equals("cd")) 
		{
			if(userStatementList.size() == constants.ONE_TEXT)
			{
				System.out.println(controller.routeName);
				System.out.print("\n");
				view.showRoute(controller.routeName);
				return;
			}
			runCd(userStatementList);
		}

		else if(userStatementList.get(0).equals("cd..") || userStatementList.get(0).equals("cd..\\") || userStatementList.get(0).equals("cd../"))
		{
			runCdDotDot(userStatementList);
		}

		else if(userStatementList.get(0).equals("cd..\\.."))
		{
			runCdDotBackSlash(userStatementList);
		}

		else if(userStatementList.get(0).equals("cd\\"))
		{
			runCdBackSlash(userStatementList);
		}

		else
		{
			view.showErrorStatement(userStatementList.get(0).toString());
			view.showRoute(controller.routeName);
		}
	}
	
	// cd ��ɹ� ���� 
	public void runCd(List<String> userStatementList) throws IOException
	{
		// cd �ڿ� �ٸ� ��ɾ ���� �� 
		if(userStatementList.get(1).equals(".."))
		{
			userStatementList.set(0, "cd..");
			userStatementList.remove(1);
			runCdDotDot(userStatementList);
			return;
		}
		else if(userStatementList.get(1).equals("..\\.."))
		{
			userStatementList.set(0, "cd..\\..");
			userStatementList.remove(1);
			runCdDotBackSlash(userStatementList);
			return;
		}
		
		else if(userStatementList.get(1).equals("\\"))
		{
			userStatementList.set(0, "cd\\");
			userStatementList.remove(1);
			runCdBackSlash(userStatementList);
			return;
		}
		
		// cd �ڿ� ��ΰ� ���� �� 
		goRoute(userStatementList);
	}

	// cd.. ��ɹ� ���� 
	public void runCdDotDot(List<String> userStatementList) throws IOException
	{
		if(userStatementList.size() > constants.ONE_TEXT)
		{
			checkInputChar(userStatementList);
			return;
		}
		backOneRoute();
		view.showRoute(controller.routeName);
	}
	
	// cd..\.. ����
	public void runCdDotBackSlash(List<String> userStatementList) throws IOException
	{
		if(userStatementList.size() > constants.ONE_TEXT)
		{
			checkInputChar(userStatementList);
			return;
		}
		backOneRoute();
		backOneRoute();
		view.showRoute(controller.routeName);
	}
	
	// cd\ ���� 
	public void runCdBackSlash(List<String> userStatementList)
	{
		if(userStatementList.size() > constants.ONE_TEXT)
		{
			if(model.isHavingText(userStatementList))
			{
				view.showNoFindRoute();
				view.showRoute(controller.routeName);
			}
			
			else
			{
				backAllRoute();
				view.showRoute(controller.routeName);
			}
			return;
		}
		backAllRoute();
		view.showRoute(controller.routeName);
	}
	
	// cd ��ɹ� �ڿ� .\/ �̿ܿ� ���ڰ� ������ ������� ���� ��� 
	public void checkInputChar(List<String> userStatementList)
	{
		if(model.isHavingText(userStatementList))
		{
			view.showNoFindRoute();
			view.showRoute(controller.routeName);
		}
		
		else
		{
			view.showRoute(controller.routeName);
		}
		return;
	}
	
	// ���� ��� �̵� 
	public void goRoute(List<String> userStatementList) throws IOException
	{
		String pathName;
		
		pathName = model.makePath2(userStatementList,controller.routeName);
		
		if(model.isExistsFile(pathName))
		{
			controller.routeName = pathName;
			view.showRoute(controller.routeName);
			return;
		}
		
		view.showNoFindRoute();
		view.showRoute(controller.routeName);
	}

	// ��ĭ �ڷΰ��� 
	public String backOneRoute() throws IOException
	{
		int seperateIndex; 
		if(controller.routeName.equals("C:")||controller.routeName.equals("c:"))
		{
			return controller.routeName;
		}

		seperateIndex = controller.routeName.lastIndexOf(File.separator); 
		controller.routeName = controller.routeName.substring(0, seperateIndex);

		return controller.routeName;
	}

	// �� ó������ ���� 
	public String backAllRoute()
	{
		controller.routeName = "C:"; 

		return controller.routeName;
	}

//	// ���ڿ����� . \ / ����
//	public String deleteChar(String str)
//	{
//		str = str.replaceAll("[.\\/]", "");	
//		str = str.replaceAll("[/]", "");
//		str = str.replace("\\", "");
//		return str;
//	}
	
}
