package java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import swing.MyProject;
import swing.UpdatePanel2;

public class Modified_Pass {

	public Modified_Pass() {
		// this.tfAge=tfAge;
		// this.tfSex=tfSex;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
//				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

			pstmt = conn.prepareStatement("update lib_users set lib_pass = (?) where lib_uid = (?)");
			// 입력 파라메터

			pstmt.setString(1, UpdatePanel2.textField_1.getText());
			pstmt.setString(2, MyProject.UserId);
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
