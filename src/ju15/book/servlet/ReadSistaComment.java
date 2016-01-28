/**
 * Author:Irina Fatkoulin
 */

package ju15.book.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ju15.book.model.CommentBean;

/**
 * Servlet implementation class ReadSistaComment
 */
@WebServlet("/ReadSistaComment")
public class ReadSistaComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadSistaComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		System.out.println("Vi ar i doPost method");
		ServletContext context = getServletContext();
		String name = request.getParameter("bild");			
	     String filename = "/WEB-INF/Feedback/" + name + ".txt";

	      
	     InputStream is = context.getResourceAsStream(filename);
  
	     if (is != null){
	    	   InputStreamReader isr = new InputStreamReader(is);
	    	   String line;
	    	   ArrayList<CommentBean> comment = new ArrayList<CommentBean>();
	    		
		    	   try{
		    	   BufferedReader reader = new BufferedReader(isr);
		    	   line = reader.readLine();
		    	   String [] data =  new String[1];
    	   
			    	   while (line !=null){
			    		   //data = line.split(";;");
			    		   //CommentBean comBean = new CommentBean(data[0]);
			    		   //comment.add(comBean);
			    		  
			    		   while(!line.contains(";;") ){
			    			   line = line+"<br> "+reader.readLine();
			    		   		}
			    		   
			    				   data = line.split(";;");
			    		   CommentBean comBean = new CommentBean(data[0]);
			    		   comment.add(comBean);
			    		   
			    		   line = reader.readLine();
		    		   if(line == null){
							break;	
							}
			    	   }
			    	   PrintWriter writer = response.getWriter();
			    	   comment.get(comment.size()-1);
			    	   writer.println("*****<br>");
			    	   writer.println(comment.get(comment.size()-1).getFeedBack()+"<br>");
		    		    writer.println("<br>");
		    		    writer.println("*****<br>");
		    		    writer.println(comment.get(comment.size()-2).getFeedBack()+"<br>");
		    		    writer.println("<br>");
			    	  System.out.println("Sista comment"+comment.get(comment.size()-1).getFeedBack());
			    	  
		    	   }catch(Exception e){
		    		   e.printStackTrace(System.out);
		    	   }
   
   
	     }
	     
	
}

	}


