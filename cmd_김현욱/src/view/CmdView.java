package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class CmdView {

	public void showWindowsVersion() throws IOException {
		Process process = Runtime.getRuntime().exec("cmd");
		InputStream inputStream = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		System.out.println(reader.readLine());
		System.out.println("(c) Microsoft Corporation. All rights reserved.");
		System.out.print("\n");
	}
	
	// ��θ� ã�� �� ���� �� �ȳ��� 
	public void showNoFindRoute()
	{
		System.out.println("������ ��θ� ã�� �� �����ϴ�."); 
		System.out.print("\n");
	}
	
	// ��ɹ��� Ʋ���� �� �ȳ��� 
	public void showErrorStatement(String statement) {
		System.out.println("'"+statement+"'"+"��(��) ���� �Ǵ� �ܺθ��, ������ �� �ִ� ���α׷�, �Ǵ�");
		System.out.println("��ġ ������ �ƴմϴ�.");
		System.out.print("\n");
	}
	
	public void showErrorConstruction()
	{
		System.out.println("���� �̸�, ���͸� �̸� �Ǵ� ���� ���̺� ������ �߸��Ǿ����ϴ�.");
		System.out.print("\n");
	}
	
	public void showErrorSyntax()
	{
		System.out.println("��� ������ �ùٸ��� �ʽ��ϴ�.");
		System.out.print("\n");
	}
	
	public void showDeniedAccess()
	{
		System.out.println("�׼����� �źεǾ����ϴ�.");
		System.out.print("\n");
	}
	
	// ���� ��� �����ֱ� 
	public void showRoute(String route) {
		System.out.print(route + ">");
	}
	
	public void showVolumeNumber()
	{
		System.out.println(" C ����̺��� �������� �̸��� �����ϴ�.");
		System.out.println(" ���� �Ϸ� ��ȣ :BEBF-2222D");
	}
	
	// ���ϵ��͸� ���
	public void showDirTop(String file) throws IOException
	{
		System.out.print("\n");
		System.out.println(" "+file+" ���͸�");
		System.out.print("\n");
	}
	
	// dir ���� �� ��� ���� 
	public void showDirectoryFormat(File file,boolean isDirectory)
	{
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.print(dataFormat.format(file.lastModified()));
		if(isDirectory)
		{
			System.out.print("    <DIR>");
			System.out.print("          ");
			System.out.print(file.getName()+"\n");
		}
		
		else
		{
			System.out.print("         ");
			System.out.printf("%9d ",file.length());
			System.out.print(file.getName()+"\n");
		}
	}
	
	public void showDirBottom(int fileCount,int directoryCount,int fileByteSum,long directoyByte)
	{
		DecimalFormat decimalFormate = new DecimalFormat("###,###");
		String fileByteString = decimalFormate.format(fileByteSum);
		String directoryByteString = decimalFormate.format(directoyByte);
		System.out.printf("%16d�� ����%20s ����Ʈ\n",fileCount,fileByteString);
		System.out.printf("%16d�� ���͸�%20s ����Ʈ\n",directoryCount,directoryByteString);
		System.out.print("\n");
	}
	
	public void showDirNoFine()
	{
		System.out.println("������ ã�� �� �����ϴ�.");
		System.out.print("\n");
	}
	
	
	
}
