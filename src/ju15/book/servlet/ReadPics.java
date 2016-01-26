package ju15.book.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ReadPics {

	
	public static ArrayList<String> getPics(String path) {
		ArrayList<String> pics=new ArrayList<String>();
		try{
			
			FileInputStream fis= new FileInputStream(path);
			BufferedReader txtReader = new BufferedReader(new InputStreamReader(fis));
			String line = txtReader.readLine();
            while (line != null) {
                
               pics.add(line);
               //System.out.println(line);
               line = txtReader.readLine();
            }
            txtReader.close();
            
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return pics;
	}
	
//	public static void main(String[] args){
//		
//		ReadPics.getPicsPath("Florida");
//		System.out.println(System.getProperty("user.dir"));
//	}

}
