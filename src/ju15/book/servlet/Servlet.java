package ju15.book.servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ju15.book.model.BookInfo;
import ju15.book.model.EmailSender;

/**
 * Servlet implementation class Servlet
 */
//@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
response.setContentType("text/html");
		
		ServletContext context = getServletContext();
////		String path=context.getRealPath(filename);
////		System.out.println(path);
//		InputStream is = context.getResourceAsStream("temp.txt");
//		if (is != null) {
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader reader = new BufferedReader(isr);
//			PrintWriter writer = response.getWriter();
//			String text = "";
//			while ((text = reader.readLine()) != null) {
//				writer.println(text);
//				System.out.println(text);
//			}
//		}
		HttpSession session=request.getSession();
		BookInfo bi=null;
		//request.getParameter("submit")
		if ("Book".equals(request.getParameter("submit"))){

			String pic = request.getParameter("bild");

			String name=request.getParameter("fullname");
			String email=request.getParameter("email");
			String date=request.getParameter("datefilterIN");
			String dateOUT=request.getParameter("datefilterOUT");
			
			String message=request.getParameter("message");
			if (message==null) message="You didn't leave any message.";

			   bi=new BookInfo(pic, name,email,date,dateOUT,message);

			
			session.setAttribute("bi", bi);
			String url = "/result.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}else if("Confirm".equals(request.getParameter("submit"))){
			if (session.getAttribute("bi")!=null){
			   bi = (BookInfo)session.getAttribute("bi");
			   try{
			      EmailSender.sendMail(bi);
			      String url = "/thanks.html";
				  getServletContext().getRequestDispatcher(url).forward(request, response);
			   }catch(Exception e){
				   String url = "/error.jsp";
				   getServletContext().getRequestDispatcher(url).forward(request, response);
			   }
			   
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
