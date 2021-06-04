package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CmdView {

	public void showWindowsVersion() throws IOException {
		Process process = Runtime.getRuntime().exec("cmd");
		InputStream inputStream = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		System.out.println(reader.readLine());
		System.out.println("(c) Microsoft Corporation. All rights reserved.");
		showBlankLine();
	}
	
	// ��θ� ã�� �� ���� �� �ȳ��� 
	public void showNoFindRoute()
	{
		System.out.println("������ ��θ� ã�� �� �����ϴ�."); 
		showBlankLine();
	}
	
	// ��ɹ��� Ʋ���� �� �ȳ��� 
	public void showErrorStatement(String statement) {
		System.out.println("\'%c\'��(��) ���� �Ǵ� �ܺθ��, ������ �� �ִ� ���α׷�, �Ǵ�");
		System.out.println("��ġ ������ �ƴմϴ�.");
		showBlankLine();
	}
	
	public void showErrorConstruction()
	{
		System.out.println("���� �̸�, ���͸� �̸� �Ǵ� ���� ���̺� ������ �߸��Ǿ����ϴ�.");
		showBlankLine();
	}
	
	public void showErrorSyntax()
	{
		System.out.println("��� ������ �ùٸ��� �ʽ��ϴ�.");
		showBlankLine();
	}
	
	public void showDeniedAccess()
	{
		System.out.println("�׼����� �źεǾ����ϴ�.");
		showBlankLine();	}
	
	// ���� ��� �����ֱ� 
	public void showRoute(String route) {
		System.out.print(route + ">");
	}
	
	public void showBlankLine()
	{
		System.out.print("\n");
	}
}
