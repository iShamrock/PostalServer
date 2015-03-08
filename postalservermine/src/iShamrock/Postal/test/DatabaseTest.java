package iShamrock.Postal.test;

import iShamrock.Postal.entity.PostalData;
import iShamrock.Postal.entity.PostalDataItem;
import iShamrock.Postal.entity.User;
import util.DatabaseConnect;

import java.util.ArrayList;

/**
 * Created by zhangqi on 15/2/21.
 */
public class DatabaseTest {
    public  static void main(String[] args){

        long beforeConnect=System.currentTimeMillis();
        DatabaseConnect dc=new DatabaseConnect();//DatabaseConnect.getconnection();
        long afterconnect=System.currentTimeMillis();
        System.out.println(afterconnect-beforeConnect);

     //   dc.register("15618036186","mypassword");
        long afterregister=System.currentTimeMillis();
        System.out.println("time of register:"+(afterregister-afterconnect));

        User u=dc.login("15618036186", "mypassword");

        long afterlogin=System.currentTimeMillis();
        System.out.println("time of login:"+(afterlogin-afterregister));



        PostalDataItem   pdi=  new PostalDataItem(0, "123", "lalala", "10:10", "this", new double[]{1.0, 2.4}, "lfs", "tzy", "here");
        PostalDataItem   pdi2= new PostalDataItem(0, "321", "lalalax", "10:10x", "thisx", new double[]{1.0, 2.4}, "lfsx", "tzyx", "herex");
        dc.addPostal(pdi);
        dc.addPostal(pdi2);
        long afterAddPostal=System.currentTimeMillis();
        System.out.println("time of addPostal:"+(afterAddPostal-afterlogin));
        ArrayList<PostalDataItem> mypostal=dc.getPostalData("lfs");

        long afterrequestpostal=System.currentTimeMillis();
        System.out.println("time of request Postal:"+(afterrequestpostal-afterAddPostal));

    }





}
