
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

public class DeleteUser{

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public DeleteUser() {
		// this.tfAge=tfAge;
		// this.tfSex=tfSex;
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
//				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");
				// 프로시저 호출
				CallableStatement cs = conn.prepareCall("begin del_user(?); end;");

				// 입력 파라메터
				cs.setString(1,MyProject.UserId);
				cs.executeUpdate();
				MyProject.UserId=null;
				cs.close();
				conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	}

}