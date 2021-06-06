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
	public void showCurrentFileInfo(long lastModifiedSecond)
	{
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.print(dataFormat.format(lastModifiedSecond));
		System.out.print("    <DIR>");
		System.out.print("          ");
		System.out.print(".\n");
		System.out.print(dataFormat.format(lastModifiedSecond));
		System.out.print("    <DIR>");
		System.out.print("          ");
		System.out.print("..\n");

	}
	
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
	
	// dir ���� ����Ʈ ��� �κ� 
	public void showDirBottom(int fileCount,int directoryCount,int fileByteSum,long directoyByte)
	{
		DecimalFormat decimalFormate = new DecimalFormat("###,###");
		String fileByteString = decimalFormate.format(fileByteSum);
		String directoryByteString = decimalFormate.format(directoyByte);
		System.out.printf("%16d�� ����%20s ����Ʈ\n",fileCount,fileByteString);
		System.out.printf("%16d�� ���͸�%20s ����Ʈ ����\n",directoryCount,directoryByteString);
		System.out.print("\n");
	}
	
	// dir �ּ� ������ ã�� �� ���� ��
	public void showDirNoFine()
	{
		System.out.println("������ ã�� �� �����ϴ�.");
		System.out.print("\n");
	}
	
	public void showClear()
	{
		for(int i=0;i<50;i++)
		{
			System.out.println("\n");	
		}
	}
	
	public void showHelp()
	{
		System.out.println("Ư�� ��ɾ ���� �ڼ��� ������ �ʿ��ϸ� HELP ��ɾ� �̸��� �Է��Ͻʽÿ�.\r\n"
				+ "ASSOC    ���� Ȯ��� ������ �����ְų� �����մϴ�.\r\n"
				+ "ATTRIB   ���� �Ӽ��� ǥ���ϰų� �ٲߴϴ�.\r\n"
				+ "BREAK    Ȯ��� CTRL+C �˻縦 �����ϰų� ����ϴ�.\r\n"
				+ "BCDEDIT        ���� �ε��� �����ϱ� ���� ���� �����ͺ��̽����� �Ӽ��� �����մϴ�.\r\n"
				+ "CACLS    ������ �׼��� ��Ʈ�� ���(ACL)�� ǥ���ϰų� �����մϴ�.\r\n"
				+ "CALL     �� �ϰ� ���α׷����� �ٸ� �ϰ� ���α׷��� ȣ���մϴ�.\r\n"
				+ "CD       ���� ���͸� �̸��� �����ְų� �ٲߴϴ�.\r\n"
				+ "CHCP     Ȱ��ȭ�� �ڵ� �������� ��ȣ�� ǥ���ϰų� �����մϴ�.\r\n"
				+ "CHDIR    ���� ���͸� �̸��� �����ְų� �ٲߴϴ�.\r\n"
				+ "CHKDSK   ��ũ�� �˻��ϰ� ���� ������ ǥ���մϴ�.\r\n"
				+ "CHKNTFS  �����ϴ� ���� ��ũ Ȯ���� ȭ�鿡 ǥ���ϰų� �����մϴ�.\r\n"
				+ "CLS      ȭ���� ����ϴ�.\r\n"
				+ "CMD      Windows ��� ������������ �� �ν��Ͻ��� �����մϴ�.\r\n"
				+ "COLOR    �ܼ��� �⺻���� ������ �����մϴ�.\r\n"
				+ "COMP     �� �� �Ǵ� ���� ���� ������ ���մϴ�.\r\n"
				+ "COMPACT  NTFS ���� ������ �ִ� ������ ������ ǥ���ϰų� �����մϴ�.\r\n"
				+ "CONVERT  FAT ������ NTFS�� ��ȯ�մϴ�. ���� ����̺��\r\n"
				+ "         ��ȯ�� �� �����ϴ�.\r\n"
				+ "COPY     �ϳ� �̻��� ������ �ٸ� ��ġ�� �����մϴ�.\r\n"
				+ "DATE     ��¥�� �����ְų� �����մϴ�.\r\n"
				+ "DEL      �ϳ� �̻��� ������ ����ϴ�.\r\n"
				+ "DIR      ���͸��� �ִ� ���ϰ� ���� ���͸� ����� �����ݴϴ�.\r\n"
				+ "DISKPART       ��ũ ��Ƽ�� �Ӽ��� ǥ���ϰų� �����մϴ�.\r\n"
				+ "DOSKEY       ������� �����ϰ�, Windows ����� �ٽ� ȣ���ϰ�,\r\n"
				+ "               ��ũ�θ� ����ϴ�.\r\n"
				+ "DRIVERQUERY    ���� ��ġ ����̹� ���¿� �Ӽ��� ǥ���մϴ�.\r\n"
				+ "ECHO           �޽����� ǥ���ϰų� ECHO�� �Ѱų� ���ϴ�.\r\n"
				+ "ENDLOCAL       ��ġ ���Ͽ��� ȯ�� ������ ����ȭ�� �����ϴ�.\r\n"
				+ "ERASE          �ϳ� �̻��� ������ ����ϴ�.\r\n"
				+ "EXIT           CMD.EXE ���α׷�(��� ����������)�� �����մϴ�.\r\n"
				+ "FC             �� ���� �Ǵ� ���� ������ ���Ͽ� �ٸ� ����\r\n"
				+ "         ǥ���մϴ�.\r\n"
				+ "FIND           ���Ͽ��� �ؽ�Ʈ ���ڿ��� �˻��մϴ�.\r\n"
				+ "FINDSTR        ���Ͽ��� ���ڿ��� �˻��մϴ�.\r\n"
				+ "FOR            ���� ������ �� ���Ͽ� ���� ������ ����� �����մϴ�.\r\n"
				+ "FORMAT         Windows���� ����� ��ũ�� �����մϴ�.\r\n"
				+ "FSUTIL         ���� �ý��� �Ӽ��� ǥ���ϰų� �����մϴ�.\r\n"
				+ "FTYPE          ���� Ȯ��� ���ῡ ���Ǵ� ���� ������ ǥ���ϰų�\r\n"
				+ "               �����մϴ�.\r\n"
				+ "GOTO           Windows ��� ���������Ͱ� �ϰ� ���α׷�����\r\n"
				+ "               �̸�ǥ�� �ٿ��� �ٷ� �̵��մϴ�.\r\n"
				+ "GPRESULT       ��ǻ�� �Ǵ� ����ڿ� ���� �׷� ��å ������ ǥ���մϴ�.\r\n"
				+ "GRAFTABL       Windows�� �׷��� ��忡�� Ȯ�� ���� ��Ʈ�� ǥ����\r\n"
				+ "         �� �ְ� �մϴ�.\r\n"
				+ "HELP           Windows ��ɿ� ���� ���� ������ �����մϴ�.\r\n"
				+ "ICACLS         ���ϰ� ���͸��� ���� ACL�� ǥ��, ����, ��� �Ǵ�\r\n"
				+ "               �����մϴ�.\r\n"
				+ "IF             �ϰ� ���α׷����� ���� ó���� �����մϴ�.\r\n"
				+ "LABEL          ��ũ�� ���� �̸��� ����ų�, �ٲٰų�, ����ϴ�.\r\n"
				+ "MD             ���͸��� ����ϴ�.\r\n"
				+ "MKDIR          ���͸��� ����ϴ�.\r\n"
				+ "MKLINK         �ٷ� ���� ��ũ�� �ϵ� ��ũ�� ����ϴ�.\r\n"
				+ "MODE           �ý��� ��ġ�� �����մϴ�.\r\n"
				+ "MORE           ����� �ѹ��� �� ȭ�龿 ǥ���մϴ�.\r\n"
				+ "MOVE           �ϳ� �̻��� ������ �� ���͸����� �ٸ� ���͸���\r\n"
				+ "               �̵��մϴ�.\r\n"
				+ "OPENFILES      ���� �������� ���� ����ڿ� ���� ���� ������ ǥ���մϴ�.\r\n"
				+ "PATH           ���� ������ ã�� ��θ� ǥ���ϰų� �����մϴ�.\r\n"
				+ "PAUSE          ��ġ ������ ó���� �Ͻ� �ߴ��ϰ� �޽����� ǥ���մϴ�.\r\n"
				+ "POPD           PUSHD�� ���� ����� ���� ���͸��� ���� ����\r\n"
				+ "               �����մϴ�.\r\n"
				+ "PRINT          �ؽ�Ʈ ������ �μ��մϴ�.\r\n"
				+ "PROMPT         Windows ��� ������Ʈ�� �����մϴ�.\r\n"
				+ "PUSHD          ���� ���͸��� ������ ���� �����մϴ�.\r\n"
				+ "RD             ���͸��� �����մϴ�.\r\n"
				+ "RECOVER        �ҷ��̰ų� ������ �ִ� ��ũ���� ���� �� �ִ� ������ �����մϴ�.\r\n"
				+ "REM            ��ġ ���� �Ǵ� CONFIG.SYS�� �ּ��� ����մϴ�.\r\n"
				+ "REN            ���� �̸��� �ٲߴϴ�.\r\n"
				+ "RENAME         ���� �̸��� �ٲߴϴ�.\r\n"
				+ "REPLACE        ������ �ٲߴϴ�.\r\n"
				+ "RMDIR          ���͸��� �����մϴ�.\r\n"
				+ "ROBOCOPY       ���ϰ� ���͸� Ʈ���� ������ �� �ִ� ��� ��ƿ��Ƽ�Դϴ�.\r\n"
				+ "SET            Windows ȯ�� ������ ǥ��, ���� �Ǵ� �����մϴ�.\r\n"
				+ "SETLOCAL       ��ġ ���Ͽ��� ȯ�� ������ ����ȭ�� �����մϴ�.\r\n"
				+ "SC             ����(��׶��� ���μ���)�� ǥ���ϰų� �����մϴ�.\r\n"
				+ "SCHTASKS       ��ǻ�Ϳ��� ������ ��ɰ� ���α׷��� �����մϴ�.\r\n"
				+ "SHIFT          ��ġ ���Ͽ��� �ٲ� �� �ִ� �Ű� ������ ��ġ�� �ٲߴϴ�.\r\n"
				+ "SHUTDOWN       ��ǻ���� ���� �Ǵ� ���� ���Ḧ ����մϴ�.\r\n"
				+ "SORT           �Է��� �����մϴ�.\r\n"
				+ "START          ������ ���α׷��̳� ����� ������ ������ â�� �����մϴ�.\r\n"
				+ "SUBST          ��θ� ����̺� ���ڿ� �����մϴ�.\r\n"
				+ "SYSTEMINFO     ��ǻ�ͺ� �Ӽ��� ������ ǥ���մϴ�.\r\n"
				+ "TASKLIST       ���񽺸� �����Ͽ� ���� ���� ���� ��� �۾��� ǥ���մϴ�.\r\n"
				+ "TASKKILL       ���� ���� ���μ����� ���� ���α׷��� �ߴ��մϴ�.\r\n"
				+ "TIME           �ý��� �ð��� ǥ���ϰų� �����մϴ�.\r\n"
				+ "TITLE          CMD.EXE ���ǿ� ���� â ������ �����մϴ�.\r\n"
				+ "TREE           ����̺� �Ǵ� ����� ���͸� ������ �׷�������\r\n"
				+ "               ǥ���մϴ�.\r\n"
				+ "TYPE           �ؽ�Ʈ ������ ������ ǥ���մϴ�.\r\n"
				+ "VER            Windows ������ ǥ���մϴ�.\r\n"
				+ "VERIFY         ������ ��ũ�� �ùٷ� ��ϵǾ����� ��������\r\n"
				+ "         ���θ� �����մϴ�.\r\n"
				+ "VOL            ��ũ ���� ���̺�� �Ϸ� ��ȣ�� ǥ���մϴ�.\r\n"
				+ "XCOPY          ���ϰ� ���͸� Ʈ���� �����մϴ�.\r\n"
				+ "WMIC           ��ȭ�� ��� �� ���� WMI ������ ǥ���մϴ�.\r\n"
				+ "\r\n"
				+ "������ ���� �ڼ��� ������ �¶��� ������ ����� ������ �����Ͻʽÿ�.\n");
	}

	// ���� ������ �������� ���� �� 
	public void showNoExistsOriginalFile()
	{
		System.out.println("������ ������ ã�� �� �����ϴ�.");
		System.out.print("\n");
	}
	
	// ���� �������� �� 
	public void showSuccessCopy()
	{
		System.out.println("        1�� ������ ����Ǿ����ϴ�.");
		System.out.print("\n");
	}
	
	// �̵� �������� �� 
	// ���� �������� �� dir
	public void showSuccessMove()
	{
		System.out.println("        1�� ������ �̵��Ǿ����ϴ�.");
		System.out.print("\n");
	}
	
	
}
