package java;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import swing.MyProject;

public class Reserve {
	String Bid;

	public Reserve(String Bid) {
		this.Bid = Bid;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CallableStatement cs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//	          conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

//	          UPDATE lib_rental 
//	          SET PRE_DATE=(select REN_DATE from lib_rental where REN_NUM=(select max(REN_NUM) from lib_rental))+14
//	          WHERE REN_NUM=(select max(REN_NUM) from lib_rental)

			String quary = "UPDATE lib_rental SET sun_id='" + MyProject.UserId + "' WHERE ren_bid='" + Bid
					+ "' and rtn_date is null";
//	          System.out.println(quary);
			pstmt = conn.prepareStatement(quary);
			pstmt.executeQuery();

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
