package GUI;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImportFile { 

public String FetchInputData(String FileName){
	if(FileName == null || FileName.equals(""))
		return "";
	
	 File file = new File(FileName);
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    String strImportFile = new String();
	    try {
	      fis = new FileInputStream(file);
	      bis = new BufferedInputStream(fis);
	      dis = new DataInputStream(bis);
	      while (dis.available() != 0) {
	        	  strImportFile = strImportFile.concat(dis.readLine());
	          }
	      fis.close();
	      bis.close();
	      dis.close();
	
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		return strImportFile;
}
}