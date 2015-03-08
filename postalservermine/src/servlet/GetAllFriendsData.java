package servlet;

import iShamrock.Postal.entity.PostalDataItem;
import iShamrock.Postal.entity.User;

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
 * Servlet implementation class GetAllFriendsData
 */
@WebServlet("/GetAllFriendsData")
public class GetAllFriendsData extends HttpServlet {
	DatabaseConnect ds=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllFriendsData() {
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
		ArrayList<User> auser=ds.getAllFriendData(phone);
		  response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    out.println(auser.size());
		for(User user:auser){
			
			    out.println(user.getName());
			    out.println(user.getPhone());
			    out.println(user.getPhotoURI());
			    out.println(user.getCoverURI());
			  
	
			    
			    
			
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
