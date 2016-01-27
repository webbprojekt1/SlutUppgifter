package ju15.book.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ju15.book.model.Availability;

/**
 * Servlet implementation class Reader
 */
@WebServlet("/Reader")
public class Reader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reader() {
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

		// System.out.println("Vi ar i doPost method");
		ServletContext context = getServletContext();
		String name = request.getParameter("bild");
		// System.out.println(name);
		String filename = "/WEB-INF/BookadDates/" + name + ".txt";
		// System.out.println(filename);
		InputStream is = context.getResourceAsStream(filename);

		if (is != null) {
			InputStreamReader isr = new InputStreamReader(is);
			String line;
			ArrayList<Availability> bookadDates = new ArrayList<Availability>();

			try {
				BufferedReader reader = new BufferedReader(isr);

				line = reader.readLine();
				String[] data = new String[2];

				while (line != null) {

					data = line.split(";");
					Availability bookad = new Availability(data[0], data[1]);
					bookadDates.add(bookad);

					line = reader.readLine();
					if (line == null) {
						break;
					}
				}

				//System.out.println("VBookad dates are " + bookadDates.get(bookadDates.size() - 1).getDate());
				Iterator<Availability> iterator = bookadDates.iterator();
				String out = "";
				PrintWriter writer = response.getWriter();
				while (iterator.hasNext()) {

					try {
						Availability element = iterator.next();
						if (iterator.hasNext()) {
							out += "'" + element.getDate() + "',";
						} else {
							// System.out.println("in try method");
							// if(element.getBookad().equals("bokad")){
							out += "'" + element.getDate() + "'";
							// writer.print("'"+element.getDate()+"',");
							//System.out.println(element.getDate());
							// }
							/*
							 * writer.println("<br>");
							 * System.out.println(element.getDate());
							 * 
							 * writer.println(element.getBookad()+"<br>");
							 * writer.println("<br>");
							 * System.out.println(element.getBookad());
							 * 
							 */}
					} catch (Exception e) {
						writer.println("Got Exception" + e);
					}
				}

				writer.println("[" + out + "]");
				//System.out.println("[" + out + "]");

			} catch (Exception e) {
				e.printStackTrace(System.out);
			}

		}

	}

}