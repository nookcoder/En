package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Copy {
	
	private Constants constants;
	
	public Copy()
	{
		this.constants = new Constants();
	}
	
	public void FileToFile(File source, File destination) throws IOException
	{
		FileInputStream origin = new FileInputStream(source);
		FileOutputStream copy = new FileOutputStream(destination);
		
		runWrite(origin,copy);
	}
	
	public void FileToDirectory(File source, File destination) throws IOException
	{
		FileInputStream origin = new FileInputStream(source);
		File temp = new File(destination.getCanonicalPath() + File.separator + source.getName());
		FileOutputStream copy = new FileOutputStream(temp);
		
		runWrite(origin,copy);
	}
	
	public void DirectoryToDirectory(File originalFile, File copyFile) throws IOException
	{
		File[] targetFile = originalFile.listFiles();
		for(File file : targetFile)
		{
			File temp = new File(copyFile.getCanonicalPath() + File.separator + file.getName());				
			
			if(file.isFile()){
				FileInputStream origin = new FileInputStream(file);
				FileOutputStream copy = new FileOutputStream(temp);
				
				runWrite(origin,copy);
			}
		}
	}
	
	public void runWrite(FileInputStream origin,FileOutputStream copy) throws IOException
	{
		int count = 0;
		while((count = origin.read()) != constants.END_FILE)
		{
			copy.write(count);
		}
		
		origin.close();
		copy.close();
	}
	
	
}
