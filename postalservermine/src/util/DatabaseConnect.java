package util;




import iShamrock.Postal.entity.PostalDataItem;
import iShamrock.Postal.entity.User;
import sun.security.util.BigInt;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by zhangqi on 15/2/21.
 */
public class DatabaseConnect {
    public final String usertablename="postaluser";
    public final String postaltablename="postal";
    public final String socialtietablename="tie";
    public Connection con=null;
    public DatabaseConnect(){
        con=getconnection();
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void ensureconnect(){
    	try {
			
			if(!con.isValid(3)){
			con=getconnection();
			 con.setAutoCommit(false);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public boolean signUp(String phone, String password)  {
        Long a= Long.valueOf((phone));


        String sql="insert into "+usertablename+"(password,phone) values('"+password+"','"+phone+"');";
        System.out.println(sql);
        if(!checkPhoneNumber(phone))
            return false;//if the name already existed
        try {

        	ensureconnect();
            Statement stmt=con.createStatement();
            //  ps.executeUpdate(sql);
            stmt.executeUpdate(sql);

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }


return true;



    }
    public void addFriend(String user, String friend){
        //todo: add a friend
        String sql="insert into "+socialtietablename+"(user1phone,user2phone) values (";
        String[] values={""+user,friend};

        for(int i=0;i<values.length;i++){

            sql+="'"+values[i]+"'";

            if(i!=values.length-1) {
                sql += ",";
            }
        }

        sql+=");";

        System.out.println(sql);
        if(!checktie(user, friend))
            return;
        try {

        	ensureconnect();
            Statement stmt=con.createStatement();
            //  ps.executeUpdate(sql);
            stmt.executeUpdate(sql);

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean checktie(String userphone,String friendphone){
        ResultSet a = null;
        String sql="select * from "+socialtietablename+" where "+"(user1phone='"+userphone+"' and user2phone='"+friendphone+"')"
                +" or "+"(user1phone='"+friendphone+"' and user2phone='"+userphone+"')";
        System.out.println(sql);

        Statement ps= null;
        try {
        	ensureconnect();
            if(!con.isClosed()) {

                ps = con.createStatement();
                a = ps.executeQuery(sql);
                con.commit();
            }


            if(! a.next() )
                return  true;




        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }



    public boolean checkPhoneNumber(String phone){
        ResultSet a = null;
        String sql="select * from "+usertablename+" where phone='"+phone+"';";
        System.out.println(sql);

        Statement ps= null;
        try {
        	ensureconnect();
        if(!con.isClosed()) {

            ps = con.createStatement();
            a = ps.executeQuery(sql);
            con.commit();
        }


        if(! a.next() )
            return  true;




        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
    public User login(String phone, String password){
        String sql="select * from "+usertablename+" where phone='"+phone+"' and "+"password='"+password+"';";
        ResultSet a=null;

        Statement ps= null;
        try {
        	ensureconnect();
            ps = con.createStatement();

        //  ps.executeUpdate(sql);
            a=ps.executeQuery(sql);
            con.commit();
            if(a.next() )  {
                String name=a.getString(4);

                String photoURI=a.getString(5);
                String coverURI=a.getString(6);
                User user = new User(name,phone,photoURI,coverURI);

                // user.Emotionlist= (ArrayList) a.getObject(7);

                return user;


            };
        } catch (SQLException e) {
            e.printStackTrace();
        }



        //todo

        return null;
    }


    public User getUser(String phone){
        String sql="select * from "+usertablename+" where phone='"+phone+"';";
        ResultSet a=null;

        Statement ps= null;
        try {
        	ensureconnect();
            ps = con.createStatement();

            //  ps.executeUpdate(sql);
            a=ps.executeQuery(sql);
            con.commit();
            if(a.next() )  {
                String name=a.getString(4);

                String photoURI=a.getString(5);
                String coverURI=a.getString(6);
                User user = new User(name,phone,photoURI,coverURI);

                // user.Emotionlist= (ArrayList) a.getObject(7);

                return user;


            };
        } catch (SQLException e) {
            e.printStackTrace();
        }



        //todo

        return null;
    }
    public ArrayList<User> getAllUser(){
        String sql="select * from "+usertablename+";";
        ResultSet a=null;

        Statement ps= null;
        ArrayList<User> allUser=new ArrayList<User>();
        try {
        	ensureconnect();
            ps = con.createStatement();

            //  ps.executeUpdate(sql);
            a=ps.executeQuery(sql);
            con.commit();
            while(a.next() )  {
                String name=a.getString(4);
                String phone=a.getString("phone");
                String photoURI=a.getString(5);
                String coverURI=a.getString(6);
                User user = new User(name,phone,photoURI,coverURI);

                // user.Emotionlist= (ArrayList) a.getObject(7);

                allUser.add(user);



            };
        } catch (SQLException e) {
            e.printStackTrace();
        }



        //todo

        return allUser;
    }

    public ArrayList<User> getAllFriendData(String userphone) {
        String sql = "select * from " + socialtietablename + " where " + "user1phone='" + userphone + "' or" +
                " user2phone='" + userphone + "';";
        ResultSet a = null;

        Statement ps = null;
        ArrayList<User> allFriend = new ArrayList<User>();
        try {
        	ensureconnect();
            ps = con.createStatement();

            //  ps.executeUpdate(sql);
            a = ps.executeQuery(sql);
            con.commit();
            while (a.next()) {
                String friendPhone = a.getString("user1phone").equals(userphone) ?
                        (a.getString("user2phone")) : a.getString("user1phone");



                User friend = this.getUser(friendPhone);

                // user.Emotionlist= (ArrayList) a.getObject(7);

                allFriend.add(friend);


            }
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //todo

        return allFriend;
    }

    public ArrayList<PostalDataItem> getPostalData(String phone){
        //todo
        String sql="select * from "+postaltablename+" where from_user='"+phone+"' or "+"to_user='"+phone+"';";
        ResultSet a=null;

        Statement ps= null;
        ArrayList<PostalDataItem> pdiArray=new ArrayList<PostalDataItem>();
        try {
        	ensureconnect();
            ps = con.createStatement();

            //  ps.executeUpdate(sql);
            a=ps.executeQuery(sql);
            con.commit();
            while(a.next() )  {
                int type=a.getInt(2);
                String uri=a.getString(3);
                String text=a.getString(4);
                String time=a.getString(5);
                String title=a.getString(6);
                double locationx=a.getDouble(7);
                double locationy=a.getDouble(8);
                double[] location=new double[]{locationx,locationy};
                String from_user=a.getString(9);
                String to_user=a.getString(10);

                String locationtext=a.getString(11);
                PostalDataItem pdi=new PostalDataItem(type,uri,text,time,title,location,from_user,to_user,locationtext);
                pdiArray.add(pdi);



                // user.Emotionlist= (ArrayList) a.getObject(7);




            };
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return pdiArray;
    }

    public void updateUserInformation(String userphone, User updatedUser) {
        String sql="update "+usertablename+" set name="+"'"+updatedUser.getName()+"'"+","+" photoURI="+"'"+updatedUser.getPhotoURI()+"'"+","+
                " coverURI="+"'"+updatedUser.getCoverURI()+"' where phone='"+userphone+"'"+";";
        System.out.println(sql);


        try {

        	ensureconnect();
            Statement stmt=con.createStatement();
            //  ps.executeUpdate(sql);
            stmt.executeUpdate(sql);

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public boolean addPostal(PostalDataItem postalDataItem){

        String sql="insert into "+postaltablename+"(type,uri,text,time,title,locationx,locationy," +
                "from_user,to_user,locationtext) values (";
        String[] values={""+postalDataItem.type,postalDataItem.uri,postalDataItem.text,postalDataItem.time,postalDataItem.title,
                ""+postalDataItem.location[0],""+postalDataItem.location[1],postalDataItem.from_user,postalDataItem.to_user,
               postalDataItem.location_text};

        for(int i=0;i<values.length;i++){

            sql+="'"+values[i]+"'";

            if(i!=values.length-1) {
                sql += ",";
            }
        }

        sql+=");";
        System.out.println(sql);

        try {

        	ensureconnect();
            Statement stmt=con.createStatement();
            //  ps.executeUpdate(sql);
            stmt.executeUpdate(sql);

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return true;




        //todo
    }


    public static Connection getconnection(){


        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try {

            String databaseName = "postal";
            String host = "10.171.248.244";
            String port = "3306";
            String username = "root";      //用户名(api key);PwOI149n9fNnjMUV5Zw0kQvN
            String password = "notroot";//密码(secret key)
            String driverName = "com.mysql.jdbc.Driver";
            String dbUrl = "jdbc:mysql://";
            String serverName = host + ":" + port + "/";
            String connName = dbUrl + serverName + databaseName;


            Class.forName(driverName);
            connection = DriverManager.getConnection(connName, username, password);
            
            return connection;

        }

        catch (Exception ex) {
            System.err.print(ex);
            System.out.println("null");
            ex.printStackTrace();

        }

        return null;
    }



}
