package java;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchBook {
	JTable table;
	String txt;
	String option;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cs = null;

	public SearchBook(JTable table, String txt, String option) {
		this.table = table;
		this.txt = txt;
		this.option = option;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

			String quary = "select * from lib_books where " + option + " like '%" + txt + "%'";

			pstmt = conn.prepareStatement(quary);
			rs = pstmt.executeQuery();
			String str[] = new String[5];
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);

			while (rs.next()) {
				str[0] = rs.getString("BID");
				str[1] = rs.getString("TITLE");
				str[2] = rs.getString("AUTHOR");
				str[3] = rs.getString("COPYRIGHT");
				str[4] = rs.getString("YEAR");

				model = (DefaultTableModel) table.getModel();
				model.addRow(str);

			}

		} catch (Exception ex) {
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
				e2.printStackTrace();
			}

		}
	}

}