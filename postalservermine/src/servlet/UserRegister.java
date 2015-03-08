package servlet;

import iShamrock.Postal.entity.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DatabaseConnect;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	DatabaseConnect ds=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        ds=new DatabaseConnect();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			if(!ds.con.isValid(3));
			ds=new DatabaseConnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String phone=(String) request.getParameter("phone");
		String password=(String)request.getParameter("password");
		System.out.println(phone+"  "+password);
   
        response.setContentType("text/html");
    	response.setCharacterEncoding("gbk");
    	if(ds.signUp(phone, password)){
    		 PrintWriter out = response.getWriter();
    		 out.println("registered successfully");
    		 out.close();
    	}
    	else{
    		PrintWriter out = response.getWriter();
   		   out.println("failed to sign up");
   		    out.close();
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
