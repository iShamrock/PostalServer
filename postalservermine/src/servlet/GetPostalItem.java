package servlet;

import iShamrock.Postal.entity.PostalDataItem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DatabaseConnect;

/**
 * Servlet implementation class getPostalItem
 */
@WebServlet("/getPostalItem")
public class GetPostalItem extends HttpServlet {
	DatabaseConnect ds=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPostalItem() {
        super();
        ds=new DatabaseConnect();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String phone=(String)request.getParameter("phone");
		ArrayList<PostalDataItem> apdi=ds.getPostalData(phone);
		  response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    out.println(apdi.size());
		for(PostalDataItem pdi:apdi){
			
			    out.println(pdi.type);
			    out.println(pdi.uri);
			    out.println(pdi.text);
			    out.println(pdi.time);
			    out.println(pdi.title);
			    out.println(pdi.location[0]);
			    out.println(pdi.location[1]);
			    out.println(pdi.from_user);
			    out.println(pdi.to_user);
			    out.println(pdi.location_text);
			    
			    
			
		}
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
