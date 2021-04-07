

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookRental{
   String Id;
   String Bid;
   //½Ã°£¿ë
   SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
   
   Connection conn = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   CallableStatement cs=null;


   public BookRental(String Id,String Bid) {
      this.Id = Id;
      this.Bid= Bid;
      
      int index =0;
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
//         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
         conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");
         
         
         String Ren_Num = "select max(ren_num) from lib_rental";
         pstmt = conn.prepareStatement(Ren_Num);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
        	 index=rs.getInt(1);
         }
         index = index+1;
         
         String format_time1 = format1.format (System.currentTimeMillis());
         java.sql.Timestamp t = java.sql.Timestamp.valueOf(format_time1);
         
//         String quary = "select count(*) from lib_users where lib_uid='"+Id+"'";
//         String quary="Insert into lib_rental (REN_NUM, REN_DATE, RTN_DATE,PRE_DATE,SUN_ID,REN_BID,REN_USER) values ("+index+", TO_DATE(SYSDATE),null,TO_DATE(SYSDATE)+14,null,'"+Bid+"','"+Id+"')";
         String quary="Insert into lib_rental (REN_NUM, REN_DATE, RTN_DATE,PRE_DATE,SUN_ID,REN_BID,REN_USER) values ("+index+",'"+t+"' ,null,TO_DATE(SYSDATE)+14,null,'"+Bid+"','"+Id+"')";
         System.out.println(quary);
         pstmt = conn.prepareStatement(quary);
         pstmt.executeQuery();
         
      } catch (Exception ex) {
         // TODO: handle exception
         ex.printStackTrace();
      } finally {
         try {
            conn.close();
            if (rs != null) {
               rs.close();
            }
            if (pstmt != null) {
               pstmt.close();
            }
            if (conn != null) {
               conn.close();
            }
            if (cs != null) {
               cs.close();
            }
         } catch (Exception e2) {
            // TODO: handle exception
            e2.printStackTrace();
         }

      }
      
      try {
          Class.forName("oracle.jdbc.driver.OracleDriver");
//          conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
          conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");
          
//          UPDATE lib_rental 
//          SET PRE_DATE=(select REN_DATE from lib_rental where REN_NUM=(select max(REN_NUM) from lib_rental))+14
//          WHERE REN_NUM=(select max(REN_NUM) from lib_rental)
          
          
         String quary="UPDATE lib_rental SET PRE_DATE=(select REN_DATE from lib_rental where REN_NUM=(select max(REN_NUM) from lib_rental))+14 WHERE REN_NUM=(select max(REN_NUM) from lib_rental)";
          System.out.println(quary);
          pstmt = conn.prepareStatement(quary);
          pstmt.executeQuery();
          
       } catch (Exception ex) {
          // TODO: handle exception
          ex.printStackTrace();
       } finally {
          try {
             conn.close();
             if (rs != null) {
                rs.close();
             }
             if (pstmt != null) {
                pstmt.close();
             }
             if (conn != null) {
                conn.close();
             }
             if (cs != null) {
                cs.close();
             }
          } catch (Exception e2) {
             // TODO: handle exception
             e2.printStackTrace();
          }

       }

   }

}