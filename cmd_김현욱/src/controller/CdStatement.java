package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.CmdModel;
import view.CmdView;

public class CdStatement {

	private CmdController controller;
	private CmdModel model;
	private CmdView view;

	public CdStatement(CmdView view, CmdController controller,CmdModel model)
	{
		this.view = view;
		this.controller = controller;
		this.model = model;
	}

	// cd ��ɹ� ���� 
	public void runCdStatement(List<String> userStatementList) throws IOException
	{
		// cd�� �Էµ����� 
		if(userStatementList.get(0).equals("cd")) 
		{
			if(userStatementList.size() == 1)
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
		if(userStatementList.size() > 1)
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
		if(userStatementList.size() > 1)
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
		if(userStatementList.size() > 1)
		{
			if(isHavingText(userStatementList))
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
		if(isHavingText(userStatementList))
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
		String path = ""; 
		
		for(int index=1;index<userStatementList.size();index++)
		{
			path = path + userStatementList.get(index);
		}
		
		pathName = controller.routeName + File.separator + path;

		if(model.isExistsFile(pathName))
		{
			controller.routeName = model.getFileRouteName(pathName);
			view.showRoute(controller.routeName);
		}

		else
		{
			view.showNoFindRoute();
			view.showRoute(controller.routeName);
		}
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

	// ���ڿ��� . / \ �� ������ ���ڿ��� �ִ� �� Ȯ�� 
	public boolean isHavingText(List<String> userStatementList)
	{
		boolean isHaving = false;

		for(int index=1; index < userStatementList.size(); index++)
		{
			if(!deleteChar(userStatementList.get(index)).equals(""))
			{
				isHaving = true;
			}
		}

		return isHaving;
	}

	// ���ڿ����� . \ / ����
	public String deleteChar(String str)
	{
		str = str.replaceAll("[.]", "");	
		str = str.replaceAll("[/]", "");
		str = str.replace("\\", "");
		return str;
	}
}
