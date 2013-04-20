package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteClassToFile {

	public static void writeFile2(String s,String nameFile) {
	    String path = "test/"+nameFile+".java";
	    try {
	    	File file = new File(path);
	    	FileWriter fw = new FileWriter(file);
	    	BufferedWriter bw = new BufferedWriter(fw);
	    	bw.write("package resultJavaTest;\n\n");
	    	bw.write("import models.*;\n");
	    	bw.write(s);
	    	bw.flush();
	    	bw.close();
	    }
	    catch(IOException e) {
	    	e.printStackTrace();
	    }
	  }
	
	
}
