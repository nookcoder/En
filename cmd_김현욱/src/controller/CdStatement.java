package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.CmdModel;
import model.Constants;
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
		if(userStatementList.get(constants.FIRST_COMMADN).equals("cd")) 
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

		else if(userStatementList.get(constants.FIRST_COMMADN).equals("cd..") || userStatementList.get(0).equals("cd..\\") || userStatementList.get(0).equals("cd../"))
		{
			runCdDotDot(userStatementList);
		}

		else if(userStatementList.get(constants.FIRST_COMMADN).equals("cd..\\.."))
		{
			runCdDotBackSlash(userStatementList);
		}

		else if(userStatementList.get(constants.FIRST_COMMADN).equals("cd\\"))
		{
			runCdBackSlash(userStatementList);
		}

		else
		{
			view.showErrorStatement(userStatementList.get(constants.FIRST_COMMADN).toString());
			view.showRoute(controller.routeName);
		}
	}
	
	// cd ��ɹ� ���� 
	public void runCd(List<String> userStatementList) throws IOException
	{
		// cd �ڿ� �ٸ� ��ɾ ���� �� 
		if(userStatementList.get(constants.SECOND_COMMAND).equals(".."))
		{
			userStatementList.set(constants.FIRST_COMMADN, "cd..");
			userStatementList.remove(constants.SECOND_COMMAND);
			runCdDotDot(userStatementList);
			return;
		}
		else if(userStatementList.get(constants.SECOND_COMMAND).equals("..\\.."))
		{
			userStatementList.set(constants.FIRST_COMMADN, "cd..\\..");
			userStatementList.remove(1);
			runCdDotBackSlash(userStatementList);
			return;
		}
		
		else if(userStatementList.get(constants.SECOND_COMMAND).equals("\\"))
		{
			userStatementList.set(constants.FIRST_COMMADN, "cd\\");
			userStatementList.remove(constants.SECOND_COMMAND);
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
		
		pathName = model.makePath(userStatementList,controller.routeName,1);
		
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
	
}
