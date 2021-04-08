package java;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FindUid {
//	private JTextField name;
//	private JTextField phone;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cs = null;

	public FindUid(JTextField name, JTextField phone) {
//		this.name=name;
//		this.phone=phone;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

			String quary = "select lib_uid from lib_users where lib_name='" + name.getText() + "' and lib_phone='"
					+ phone.getText() + "'";

			pstmt = conn.prepareStatement(quary);
			rs = pstmt.executeQuery();

			String str = "";
			while (rs.next()) {
				str = rs.getString("lib_uid");
			}
			if (str.equals("")) {

				JOptionPane.showMessageDialog(null, "없는 사용자입니다");
			} else {
				JOptionPane.showMessageDialog(null, "아이디는 " + str + "입니다");
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
