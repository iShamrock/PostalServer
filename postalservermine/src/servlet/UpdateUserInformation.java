package servlet;

import iShamrock.Postal.entity.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DatabaseConnect;

/**
 * Servlet implementation class UpdateUserInformation
 */
@WebServlet("/UpdateUserInformation")
public class UpdateUserInformation extends HttpServlet {
	DatabaseConnect ds;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserInformation() {
        super();
        ds=new DatabaseConnect();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	String name=(String) request.getParameter("name");
		String phone=(String)request.getParameter("phone");
	//	String photoURI=(String) request.getParameter("photoURI");
	//	String coverURI=(String)request.getParameter("coverURI");
		String upname=(String) request.getParameter("name2");
		String upphone=(String)request.getParameter("phone2");
		String upphotoURI=(String) request.getParameter("photoURI2");
		String upcoverURI=(String)request.getParameter("coverURI2");
		if(upphone.equals(phone)){
			User updatedUser=new User(upname,upphone,upphotoURI,upcoverURI);
			ds.updateUserInformation(phone, updatedUser);
			
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
