package servlet;

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
 * Servlet implementation class userlogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	DatabaseConnect ds=null;//new DatabaseConnect();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
    	
        super();
        ds=new DatabaseConnect();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String phone=(String) request.getParameter("phone");
		String password=(String)request.getParameter("password");
	//	System.out.println(phone+"  "+password);
        User user=ds.login(phone,password);
    	response.setCharacterEncoding("gbk");
   
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println(user.getName());
    out.println(user.getPhone());
    out.println(user.getPhotoURI());
    out.println(user.getCoverURI());
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
