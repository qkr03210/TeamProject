
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
}