
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javafx.scene.control.PasswordField;

public class Check {
	JTable table;
	String txt;
	Scanner scan = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cs=null;


	public Check(JTable table,String txt) {
		this.table = table;
		this.txt= txt;
		
	}
	public Check(String txt) {
		this.txt= txt;
	}
	public Check(){
		
	}
	public void setTxt(String txt)
	{
		this.txt=txt;
	}
	public int checkId()
	{
		int index=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

			String quary = "select count(*) from lib_users where lib_uid='"+txt+"'";
			

			pstmt = conn.prepareStatement(quary);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				index = Integer.parseInt( rs.getString(1));
			}
			
			if(index!=0)
			{
				return index;
			}
			else
				return index;
			
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
				return index;
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return index;
	}
	
	public int checkName()
	{
		int index=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

			String quary = "select count(*) from lib_users where LIB_NAME='"+txt+"'";
//			LIB_PHONE

			pstmt = conn.prepareStatement(quary);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				index = Integer.parseInt( rs.getString(1));
			}
			
			if(index!=0)
			{
				JOptionPane.showMessageDialog(null,"중복입니다");
				return index;
			}
			else
				return index;
			
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
				return index;
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return index;
	}
	public boolean checkPwd(String id,String inputPwd)
	{
		String pwd="";
		boolean flag = false;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

			String quary = "select lib_pass from lib_users where LIB_UID='"+id+"'";
//			LIB_PHONE

			pstmt = conn.prepareStatement(quary);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pwd = rs.getString(1);
			}
			
			if(inputPwd.equals(pwd))
			{
				flag=true;
				return flag;
			}
			else
			{
				return flag;
			}
				
			
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
				return flag;
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return flag;
	}
	public int checkPhone()
	{
		int index=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

			String quary = "select count(*) from lib_users where LIB_PHONE='"+txt+"'";
//			LIB_PHONE

			pstmt = conn.prepareStatement(quary);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				index = Integer.parseInt( rs.getString(1));
			}
			
			if(index!=0)
			{
				JOptionPane.showMessageDialog(null,"중복입니다");
				return index;
			}
			else
				return index;
			
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
				return index;
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return index;
	}
	public String checkRental(){
		String rent="대여가능";
		int index=-1;
		int index2=-1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

			//대여된것이 없음 or 빌려가고 반납함 예약x
			
			String quary = "select count(*) from lib_rental where (ren_bid='"+txt+"')or(ren_bid='"+txt+"' and rtn_date is not null and sun_id is null)";

			pstmt = conn.prepareStatement(quary);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				index = Integer.parseInt( rs.getString(1));
			}
			
			//일단 bid로 검색이 되어야하고 반납x 예약x ->1
			String quary2 = "select count(*) from lib_rental where ren_bid='"+txt+"' and rtn_date is null and sun_id is null";

			pstmt = conn.prepareStatement(quary2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				index2 = Integer.parseInt( rs.getString(1));
			}
			
			if(index==0 )
			{
				rent = "대여가능";
			}
			else
			{
				if(index2==1)
				{
					rent ="예약가능";
				}
				else
					rent="예약중";
			}
			
			return rent;
				
			
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
		
		return null;
	}
		// 패스워드필드를 string으로 변환 메소드 (김민성)
	public String get_Pass(JPasswordField passwordField) {
		passwordField.setEchoChar('*');
		String pw = "";
		char[] secret_pw = passwordField.getPassword();
    
		for (char cha : secret_pw) {
			Character.toString(cha); 
       //cha 에 저장된 값 string으로 변환 //pw 에 저장하기, pw 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자 pw += (pw.equals("")) ? ""+cha+"" : ""+cha+""; }

			pw += (pw.equals("")) ? ""+cha+"" : ""+cha+"";

       
		}
    
    return pw;
    
 }
}