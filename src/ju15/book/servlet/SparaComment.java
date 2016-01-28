/**
 * Author:Irina Fatkoulin
 */

package ju15.book.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ju15.book.model.CommentBean;

/**
 * Servlet implementation class SparaComment
 */
@WebServlet("/SparaComment")
public class SparaComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SparaComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ServletContext context = getServletContext();
		//HttpSession session = request.getSession();
		String name = request.getParameter("nameOfHouse");			
	     String filename = "/WebContent/WEB-INF/Feedback/" + name + ".txt";
	     System.out.println("Filename is "+ filename);
		String feedback = request.getParameter("feedback");
		CommentBean i = new CommentBean(feedback);
		System.out.println("Feedback is: " + i.getFeedBack());
		try {
			String content = feedback;

			String wd = System.getProperty("user.dir");
			// System.out.println(wd);
			File file = new File(wd + filename);

			// Here true is to append the content to file
			FileWriter fw = new FileWriter(file, true);
			// BufferedWriter writer give better performance
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("\n" + content + ";;");
			// Closing BufferedWriter Stream
			bw.close();
			System.out.println("Data successfully appended at the end of file");

			} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}
		//try {
		//	session.invalidate();
		//	session = request.getSession(true);
			// ServletContext = request.getServletContext();
			// HttpServletRequest r = (HttpServletRequest) request;

		//} catch (Exception e) {
		//}
		String url = "/husbook.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
	
	}

}
