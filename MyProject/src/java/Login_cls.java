package java;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Login_cls {
	String Id;
	String Pwd;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cs = null;

	public Login_cls(String Id, String Pwd) {
		this.Id = Id;
		this.Pwd = Pwd;
	}

	public int checkLogin() {
		// -1 ->초기값
		// 0 ->없는 사용자
		// 2 ->로그인 성공
		// 1 ->비밀번호 틀림

		int index = -1;
		String str = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

			String quary = "select count(*) from lib_users where lib_uid='" + Id + "'";

			pstmt = conn.prepareStatement(quary);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				index = Integer.parseInt(rs.getString(1));
			}

			if (index != 0) {
				quary = "select lib_pass from lib_users where lib_uid='" + Id + "'";

				pstmt = conn.prepareStatement(quary);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					str = rs.getString(1);
				}

				if (str.equals(Pwd)) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
					index = 2;
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다");
					index = 1;
				}
			} else {
				JOptionPane.showMessageDialog(null, "없는 사용자입니다");
				index = 0;
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
		return index;
	}

}