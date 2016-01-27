package ju15.book.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ju15.book.model.BookInfo;
import ju15.book.model.EmailSender;

/**
 * Servlet implementation class Servlet
 */
// @WebServlet("/Servlet")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		HttpSession session = request.getSession();
		BookInfo bi = null;
		// request.getParameter("submit")
		if ("Book".equals(request.getParameter("submit"))) {

			String pic = request.getParameter("bild");

			String name = request.getParameter("fullname");
			String email = request.getParameter("email");
			String date = request.getParameter("datefilterIN");
			String dateOUT = request.getParameter("datefilterOUT");

			String message = request.getParameter("message");
			if (message == "")
				message = "You didn't leave any message.";

			bi = new BookInfo(pic, name, email, date, dateOUT, message);

			session.setAttribute("bi", bi);
			String url = "/result.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		} else if ("Confirm".equals(request.getParameter("submit"))) {
			if (session.getAttribute("bi") != null) {
				bi = (BookInfo) session.getAttribute("bi");
				try {
					EmailSender.sendMail(bi);
					session.invalidate();
					session=request.getSession();
					String url = "/thanks.html";
					getServletContext().getRequestDispatcher(url).forward(request, response);
				} catch (Exception e) {
					
					String url = "/error.jsp";
					getServletContext().getRequestDispatcher(url).forward(request, response);
				}

			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
