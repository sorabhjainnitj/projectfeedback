/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedbackmngt;

/**
 *
 * @author sorabh
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class database 
{
    public  Connection con;
    PreparedStatement pst;
    ResultSet rs;
    database()
    {
        try{
             
            //MAKE SURE YOU KEEP THE mysql_connector.jar file in java/lib folder
            //ALSO SET THE CLASSPATH
          //  Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/feedbackmngt","root","12345");
                     
             
           }
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
        //ip:username,password
        //return boolean
    public Boolean checkLogin(String uname,String pwd)
    {
        try {
            pst=con.prepareStatement("select * from logintable where rollno=? and password=?");                        
            pst.setString(1, uname); //this replaces the 1st  "?" in the query for username
            pst.setString(2, pwd);    //this replaces the 2st  "?" in the query for password
            //executes the prepared statement
            rs=pst.executeQuery();
            if(rs.next())
            {
                //TRUE iff the query founds any corresponding data
                return true;
            }
            else
            {
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while validating"+e);
            return false;
        }
    }
   public int addstudent(String name,String branch,String rollno,String year,String semester,String email,String password)
   {   
        try{
             String a="insert into logintable value(?,?,?,?,?,?,?)";
            // JOptionPane.showMessageDialog(rootPane,"abceewwwwccc");
             PreparedStatement mystat=con.prepareStatement(a);
             mystat.setString(1,name);
             mystat.setString(2,branch);
             mystat.setString(3,rollno);
             mystat.setString(4,year);
             mystat.setString(5,semester);
             mystat.setString(6,email);
             mystat.setString(7,password);
             if(mystat.executeUpdate()==1)
             {
                 return 1;
                
             }
             else
             {  return 0;
                 
             }
         }
        
         
         catch(Exception e){
           System.out.println("Error in query"+e.getMessage());
         }
        return 1;
   }
}
