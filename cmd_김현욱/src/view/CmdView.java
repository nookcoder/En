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
	}
	
	// ��θ� ã�� �� ���� �� �ȳ��� 
	public void showNoFindRoute()
	{
		System.out.println("������ ��θ� ã�� �� �����ϴ�."); 
	}
	
	// ��ɹ��� Ʋ���� �� �ȳ��� 
	public void showErrorStatement(String statement) {
		System.out.println("\'%c\'��(��) ���� �Ǵ� �ܺθ��, ������ �� �ִ� ���α׷�, �Ǵ�");
		System.out.println("��ġ ������ �ƴմϴ�.");
	}
	
	// ���� ��� �����ֱ� 
	public void showRoute(String route) {
		System.out.print("\n");
		System.out.print(route + ">");
	}
}
