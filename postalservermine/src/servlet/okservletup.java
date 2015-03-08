package servlet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class okservletup
 */
@WebServlet("/okservletup")
public class okservletup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;    // 文件存放目录  
    private String tempPath;    // 临时文件目录  
 
    // 初始化  
    public void init(ServletConfig config) throws ServletException  
    {  
        super.init(config);  
        // 从配置文件中获得初始化参数  
        filePath = config.getInitParameter("filepath");  
        tempPath = config.getInitParameter("temppath");  
        
        ServletContext context = getServletContext();  
 
     //   filePath = context.getRealPath(filePath); 
        filePath="/Users/zhangqi/desktop/pic/";
        tempPath = "/Users/zhangqi/desktop/pic/temp/"  ;
        System.out.println("文件存放目录准备完毕");  
    }  
      
    // doPost  
    public void doPost(HttpServletRequest req, HttpServletResponse res)  
        throws IOException, ServletException  
    {  
    	
    	
    	try{
    	System.out.println("uploadreach");
        res.setContentType("text/plain;charset=gbk");  
        req.getInputStream(); 
    //    OutputStream ops= res.getOutputStream();
        
        DataInputStream dis= new DataInputStream(req.getInputStream());
        String uri=(String)req.getParameter("name");
        
        
      String filediscribe=dis.readUTF();
      String filename=filediscribe.substring(filediscribe.indexOf("filename=")+"filename=".length(),filediscribe.length());
      filename=filename.split("\"")[1];
      String phone=filediscribe.substring(filediscribe.indexOf("phone=")+"phone=".length(),filediscribe.length());
      phone=phone.split("\"")[1];
    
      System.out.println(filediscribe);
      System.out.println(filename);
     
     
        
   //    FileInputStream fi=new FileInputStream(filePath+"pp");
        String basePath = req.getSession().getServletContext().getRealPath("/img/");  
        File f=new File(basePath+"/"+phone);
        if(!f.exists()){
        	f.mkdir();
        }
       FileOutputStream fo=new FileOutputStream(basePath+"/"+phone+"/"+filename);
       System.out.println(basePath+"/"+filename);
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len=dis.read(bytes))!=-1){
        	fo.write(bytes,0,len);
        	System.out.println("disreading!");
        }
    	
        fo.close();
        dis.close();
    }catch(IOException e){
    	System.out.print(e);
    }
     /*{  
            DiskFileItemFactory diskFactory = new DiskFileItemFactory();  
            // threshold 极限、临界值，即硬盘缓存 1M  
            diskFactory.setSizeThreshold(4 * 1024);  
            // repository 贮藏室，即临时文件目录  
            diskFactory.setRepository(new File(tempPath));  
          
            ServletFileUpload upload = new ServletFileUpload(diskFactory);  
            // 设置允许上传的最大文件大小 4M  
            upload.setSizeMax(4 * 1024 * 1024);  
            // 解析HTTP请求消息头  
            List fileItems = upload.parseRequest((RequestContext) req);  
            Iterator iter = fileItems.iterator();  
            while(iter.hasNext())  
            {  
                FileItem item = (FileItem)iter.next();  
                if(item.isFormField())  
                {  
                    System.out.println("处理表单内容 ...");  
                    processFormField(item, pw);  
                }else{  
                    System.out.println("处理上传的文件 ...");  
                    processUploadFile(item, pw);  
                }  
            }// end while()  
 
            pw.close();  
        }catch(Exception e){  
            System.out.println("使用 fileupload 包时发生异常 ...");  
            e.printStackTrace();  
        }// end try ... catch ...  
     */
    }// end doPost()  
 
 
    // 处理表单内容  
    private void processFormField(FileItem item, PrintWriter pw)  
        throws Exception  
    {  
        String name = item.getFieldName();  
        String value = item.getString();          
        pw.println(name + " : " + value + "\n");  
    }  
      
    // 处理上传的文件  
    private void processUploadFile(FileItem item, PrintWriter pw)  
        throws Exception  
    {  
        // 此时的文件名包含了完整的路径，得注意加工一下  
        String filename = item.getName();         
        System.out.println("完整的文件名：" + filename);  
        int index = filename.lastIndexOf("/");  
        filename = filename.substring(index + 1, filename.length());  
 
        long fileSize = item.getSize();  
 
        if("".equals(filename) && fileSize == 0)  
        {             
            System.out.println("文件名为空 ...");  
            return;  
        }  
 
        File uploadFile = new File(filePath + "/" + filename);  
        item.write(uploadFile);  
        pw.println(filename + " 文件保存完毕 ...");  
        pw.println("文件大小为 ：" + fileSize + "\n");  
    }  
      
    // doGet  
    public void doGet(HttpServletRequest req, HttpServletResponse res)  
        throws IOException, ServletException  
    {  
        doPost(req, res);  
    }  
}
