package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnManager {
	// variables
	private static Connection conn = null;
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";

	// method for db conn
	private static Connection DBConn(String ID, String PW) {
		try {
			//
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, ID, PW);
			if (conn != null) {
				System.out.println("접속 성공");
			} else
				System.out.println("접속 실패");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패:\n" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패:\n" + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error\n" + e.toString());
		}
		return conn;
	}

	public static Connection getConn(String x, String y) {
		if (conn == null)
			return DBConn(x, y);
		else
			return conn;
	}

	public static void disconn() {
		try {
			if (conn != null)
				conn = null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
