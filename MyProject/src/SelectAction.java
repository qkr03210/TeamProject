
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SelectAction {
	JTable table;
	int page;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cs=null;


	public SelectAction(JTable table, int page) {
		this.table = table;
		this.page = page;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");


			// 프로시저를 사용한 select문(이재문)
			cs = conn.prepareCall("begin proc_lib_pagination(?,?,?,?,?); end;");
			cs.setInt(1, page*10000);
			cs.setInt(2, (page+1)*10000);
			cs.setString(3, "BID");
			cs.setString(4, "y");

			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);

			cs.execute();
			rs = (ResultSet) cs.getObject(5);
			String str[] = new String[5];
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			int temp =0;
			while (rs.next()) {
				temp++;
				str[0] = rs.getString("BID");
				str[1] = rs.getString("TITLE");
				str[2] = rs.getString("AUTHOR");
				str[3] = rs.getString("COPYRIGHT");
				str[4] = rs.getString("YEAR");
				
				model = (DefaultTableModel) table.getModel();
				model.addRow(str);

			}
//			System.out.println("row count = "+temp);

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