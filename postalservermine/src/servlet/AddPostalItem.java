package servlet;

import iShamrock.Postal.entity.PostalDataItem;
import iShamrock.Postal.entity.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DatabaseConnect;

/**
 * Servlet implementation class addPostalItem
 */
@WebServlet("/addPostalItem")
public class AddPostalItem extends HttpServlet {
	DatabaseConnect ds=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPostalItem() {
        super();
        ds=new DatabaseConnect();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int  type=Integer.valueOf( request.getParameter("type"));
		String uri=(String)request.getParameter("uri");
		String text=(String)request.getParameter("text");
		String time=(String)request.getParameter("time");
		String title=(String)request.getParameter("title");
		double locationx=Double.valueOf(request.getParameter("locationx"));
		double locationy=Double.valueOf(request.getParameter("locationy"));
		String from_user=(String) request.getParameter("from");
		String to_user=(String) request.getParameter("to");
		String location_text=(String)request.getParameter("locationtext");
		double[] location={locationx,locationy};
		PostalDataItem pdi=new PostalDataItem(type,uri,text,time,title, location,from_user,to_user,location_text);
         ds.addPostal(pdi);
    	response.setCharacterEncoding("gbk");
   	
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("postal added sucessfully");
    out.close();
    
    
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
