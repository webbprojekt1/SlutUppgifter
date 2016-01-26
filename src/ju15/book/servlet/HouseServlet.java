/**
 * Author: Bo Yang
 */

package ju15.book.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ju15.book.model.House;

/**
 * Servlet implementation class House
 */
@WebServlet("/House")
public class HouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private House Mallorca=null;
	private House Florida=null;
	private House ibiza=null;
	private House croatia=null;
    private static ArrayList<House> houses=null;
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HouseServlet() {
    	 Mallorca=new House("Mallorca","img/bild6.jpg","Mallorca");
    	 Florida=new House("Florida","img/bild2.jpg","Lorem ipsum dolor sit amet,"
    	 		+ " eos id wisi noluisse voluptatum, ornatus volumus ei usu. Sumo scripta sed ne, ad novum iuvaret eos, ceteros omnesque ponderum usu at. Sed novum mediocrem democritum et, mucius nemore mea id. Dolore platonem est ea, qui te regione integre, quod debitis sit id. Nec in ipsum mucius docendi, vero voluptatibus concludaturque per eu, probo clita adolescens quo te.");
    	 ibiza=new House("Ibiza","img/bild3.jpg","Ibiza");
    	 croatia=new House("Croatia","img/bild4.jpg","Croatia");
    	 houses=new ArrayList<House>();
         houses.add(Mallorca);
         houses.add(Florida);
         houses.add(ibiza);
         houses.add(croatia);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		String bild=request.getParameter("bild");
		HttpSession session=request.getSession();
		//System.out.println("bild is "+bild);
		House house=null;
		for (int i=0;i<houses.size();i++){
			if (houses.get(i).getName().equals(bild)){
				
				house=houses.get(i);
				//System.out.println(house.getUrl());
				session.setAttribute("house", house);
				break;
			}
		}
		String path=sc.getRealPath("/Web-INF/"+bild+".txt");
		//System.out.println(path);
		ArrayList<String> pics=ReadPics.getPics(path);
		session.setAttribute("pics", pics);
		String url = "/husbook.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
