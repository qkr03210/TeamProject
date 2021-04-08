package java;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

//db에 등록
public class UserAdd {
//	JTextField Name;
//	JTextField pNum;
//	JPasswordField Pwd;
//	JTextField Uid;

	Scanner scan = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public UserAdd(JTextField Uid, JPasswordField Pwd, JTextField Name, JTextField pNum) {
		String r_pwd = Helper.get_Pass(Pwd);
		// 비밀번호 필드로 변경 (김민성)
//		this.Name = Name;
//		this.pNum = pNum;
//		this.Uid = Uid;
//		this.Pwd = Pwd;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");

			// 프로시저 호출
			CallableStatement cs = conn.prepareCall("begin add_user(?,?,?,?); end;");

			String str[] = new String[4];
			str[0] = Uid.getText();
			str[1] = r_pwd;
			str[2] = Name.getText();
			/// edit by jaemoonnlee
			// pNum.getText() => pNum.getText().replace("-", "").replace(".", "")
			str[3] = pNum.getText().replace("-", "").replace(".", "");

			// 입력 파라메터
			cs.setString(1, str[0]);
			cs.setString(2, str[1]);
			cs.setString(3, str[2]);
			cs.setString(4, str[3]);

			cs.executeUpdate();

//			rs.close();
			cs.close();
			conn.close();

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
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}
}