package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java_side.Helper;
import swing.MyProject;

public class DML {
	private Connection tempConn = null;
	// PreparedStatement: 쿼리에 인자부여 가능|여러번 수행시 유리|처음 한번 컴파일
	// Statement: 인자부여 불가능|단일 수행시 유리|매번 컴파일
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private CallableStatement cs = null;
	private ResultSet rs = null;

	public DML() {
		//
	}

	/**
	 * 도서 전체를 10000으로 나눈 몫
	 * 
	 * @return
	 */
	public int countAllBooks() {
		int idx = -1;
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * SELECT CAST((COUNT(*)/10000) AS NUMBER(4)) FROM LIB_BOOKS
			 */

			String query = "SELECT CAST((COUNT(*)/10000) AS NUMBER(4)) FROM LIB_BOOKS";
			pstmt = tempConn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next())
				idx = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return idx;
	}

	/**
	 * 매개변수로 받은 테이블에 해당 페이지(offset)의 정보(0~10000개의 행)를 담는다.
	 * 
	 * @param table
	 * @param page
	 */
	public void selectBooks(JTable table, int page) {
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * 프로시저 사용 BEGIN PROC_LIB_PAGINATION(?,?,?,?,?); END;
			 */
			cs = tempConn.prepareCall("BEGIN PROC_LIB_PAGINATION(?,?,?,?,?); END;");
			cs.setInt(1, page * 10000);
			cs.setInt(2, (page + 1) * 10000);
			cs.setString(3, "BID");
			cs.setString(4, "y");

			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);

			cs.execute();
			rs = (ResultSet) cs.getObject(5);
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 책을 유저에게 대여.
	 * 
	 * @param userId
	 * @param bookId
	 */
	public void rentBook(String userId, String bookId) {
		//
		int index = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * SELECT MAX(REN_NUM) FROM LIB_RENTAL
			 */
			String Ren_Num = "SELECT MAX(REN_NUM) FROM LIB_RENTAL";
			pstmt = tempConn.prepareStatement(Ren_Num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				index = rs.getInt(1);
			}
			index = index + 1;

			/*
			 * Insert into lib_rental (REN_NUM, REN_DATE,
			 * RTN_DATE,PRE_DATE,SUN_ID,REN_BID,REN_USER) values (대여번호, 'yyyy-MM-dd
			 * HH:mm:ss', null, TO_DATE(SYSDATE) + 14, null, '책번호','유저아이디')
			 */
			String yyMMddHHmmss = sdf.format(System.currentTimeMillis());
			java.sql.Timestamp time = java.sql.Timestamp.valueOf(yyMMddHHmmss);

			String query01 = "INSERT INTO LIB_RENTAL (REN_NUM, REN_DATE, RTN_DATE,PRE_DATE,SUN_ID,REN_BID,REN_USER) VALUES ("
					+ index + ",'" + time + "' ,NULL,TO_DATE(SYSDATE)+14,NULL,'" + bookId + "','" + userId + "')";
//			System.out.println(query01);
			pstmt = tempConn.prepareStatement(query01);
			pstmt.executeQuery();

			/*
			 * UPDATE LIB_RENTAL SET PRE_DATE = ( SELECT REN_DATE FROM LIB_RENTAL WHERE
			 * REN_NUM = ( SELECT MAX(REN_NUM) FROM LIB_RENTAL)) + 14 WHERE REN_NUM = (
			 * SELECT MAX(REN_NUM) FROM LIB_RENTAL)
			 */
			String query02 = "UPDATE LIB_RENTAL SET PRE_DATE=(SELECT REN_DATE FROM LIB_RENTAL WHERE REN_NUM=(SELECT MAX(REN_NUM) FROM LIB_RENTAL))+14 WHERE REN_NUM=(SELECT MAX(REN_NUM) FROM LIB_RENTAL)";
			System.out.println(query02);
			pstmt = tempConn.prepareStatement(query02);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 유저 삭제 후 초기화면
	 */
	public void deleteUser() {
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * BEGIN DEL_USER(?); END;
			 */

			// 프로시저 호출
			cs = tempConn.prepareCall("BEGIN DEL_USER(?); END;");

			// 입력 파라메터
			cs.setString(1, MyProject.UserId);
			cs.executeUpdate();
			MyProject.UserId = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 아이디 찾기
	 * 
	 * @param name
	 * @param phone
	 */
	public void findId(JTextField name, JTextField phone) {
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * SELECT LIB_UID FROM LIB_USERS WHERE LIB_NAME = '유저이름' AND LIB_PHONE =
			 * '유저전화번호'
			 */

			String query = "SELECT LIB_UID FROM LIB_USERS WHERE LIB_NAME='" + name.getText() + "' AND LIB_PHONE='"
					+ phone.getText() + "'";

			pstmt = tempConn.prepareStatement(query);
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		return null;
	}

	/**
	 * 비밀번호 찾기
	 * 
	 * @param uid
	 * @param name
	 * @param phone
	 */
	public void findPw(JTextField uid, JTextField name, JTextField phone) {
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * SELECT LIB_PASS FROM LIB_USERS WHERE LIB_UID = '유저아이디' AND LIB_NAME = '유저이름'
			 * AND LIB_PHONE = '유저전화번호'
			 */

			String query = "SELECT LIB_PASS FROM LIB_USERS WHERE LIB_UID = '" + uid.getText() + "' and lib_name = '"
					+ name.getText() + "' AND LIB_PHONE = '" + phone.getText() + "'";

			pstmt = tempConn.prepareStatement(query);
			rs = pstmt.executeQuery();
			String str = "";
			while (rs.next()) {
				str = rs.getString("LIB_PASS");
			}

			if (str.equals(""))
				JOptionPane.showMessageDialog(null, "없는 사용자입니다");
			else
				JOptionPane.showMessageDialog(null, "비밀번호는 " + str + "입니다");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 로그인
	 * 
	 * @param id
	 * @param pw
	 * @return 0: 없는 사용자 | 1: 비밀번호 오류 | 2: 로그인 성공
	 */
	public int login(String id, JPasswordField pw) {
		int idx = -1;
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * SELECT COUNT(*) FROM LIB_USERS WHERE LIB_UID = '유저아이디'
			 */

			String query = "SELECT COUNT(*) FROM LIB_USERS WHERE LIB_UID='" + id + "'";

			pstmt = tempConn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				idx = Integer.parseInt(rs.getString(1));
			}

			// 아이디 존재
			if (idx != 0) {
				if (checkPw(id, pw)) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
					idx = 2;
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다");
					idx = 1;
				}
			}
			// 아이디 비존재
			else {
				JOptionPane.showMessageDialog(null, "없는 사용자입니다");
				idx = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return idx;
	}

	/**
	 * 비밀번호 변경
	 * 
	 * @param pw 새 비밀번호
	 */
	public void changePw(JTextField pw) {
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * UPDATE LIB_USERS SET LIB_PASS = (?) WHERE LIB_UID = (?)
			 */

			pstmt = tempConn.prepareStatement("UPDATE LIB_USERS SET LIB_PASS = (?) WHERE LIB_UID = (?)");
			// 입력 파라메터
			pstmt.setString(1, pw.getText());
			pstmt.setString(2, MyProject.UserId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 매개변수로 받은 책번호를 현재 접속 중인 유저에게 예약
	 * 
	 * @param bookId
	 */
	public void reserveBook(String bookId) {
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * UPDATE LIB_RENTAL SET SUN_ID = '유저아이디' WHERE REN_BID = '책번호' AND RTN_DATE IS
			 * NULL
			 */

			String query = "UPDATE LIB_RENTAL SET SUN_ID='" + MyProject.UserId + "' WHERE REN_BID='" + bookId
					+ "' AND RTN_DATE IS NULL";
			pstmt = tempConn.prepareStatement(query);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param table
	 * @param txt
	 * @param option
	 */
	public void searchBook(JTable table, String txt, String option) {
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * SELECT * FROM LIB_BOOKS WHERE " + OPTION + " LIKE '%" + TXT + "%'
			 */

			String query = "SELECT * FROM LIB_BOOKS WHERE " + option + " LIKE '%" + txt + "%'";

			pstmt = tempConn.prepareStatement(query);
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 유저 등록
	 * 
	 * @param Uid
	 * @param Pwd
	 * @param Name
	 * @param pNum
	 */
	public void addUser(JTextField Uid, JPasswordField Pwd, JTextField Name, JTextField pNum) {
		String r_pwd = Helper.get_Pass(Pwd);
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * BEGIN ADD_USER(?,?,?,?); END;
			 */

			// 프로시저 호출
			CallableStatement cs = tempConn.prepareCall("BEGIN ADD_USER(?,?,?,?); END;");

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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 도서 대여 여부
	 * 
	 * @param bookId
	 * @return
	 */
	public String canRent(String bookId) {
		String result = "대여가능";
		int index = -1;
		int index2 = -1;
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * SELECT COUNT(*) FROM LIB_RENTAL WHERE (REN_BID = '책번호') OR (REN_BID = '책번호'
			 * AND RTN_DATE IS NOT NULL AND SUN_ID IS NULL)
			 */

			String query = "SELECT COUNT(*) FROM LIB_RENTAL WHERE (REN_BID='" + bookId + "')OR(REN_BID='" + bookId
					+ "' AND RTN_DATE IS NOT NULL AND SUN_ID IS NULL)";

			pstmt = tempConn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				index = Integer.parseInt(rs.getString(1));
			}

			/*
			 * SELECT COUNT(*) FROM LIB_RENTAL WHERE REN_BID='책번호' AND RTN_DATE IS NULL AND
			 * SUN_ID IS NULL
			 */
			// 일단 bid로 검색이 되어야하고 반납x 예약x ->1
			String quary2 = "SELECT COUNT(*) FROM LIB_RENTAL WHERE REN_BID='" + bookId
					+ "' AND RTN_DATE IS NULL AND SUN_ID IS NULL";

			pstmt = tempConn.prepareStatement(quary2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				index2 = Integer.parseInt(rs.getString(1));
			}

			if (index == 0) {
				result = "대여가능";
			} else {
				if (index2 == 1) {
					result = "예약가능";
				} else
					result = "예약중";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 매개변수id가 db상에 존재하는지 확인
	 * 
	 * @param id
	 * @return -1: ERROR | 0: 중복 없음 | 1: 중복 존재
	 */
	public int checkIdDup(String id) {
		int index = -1;
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * SELECT COUNT(*) FROM LIB_USERS WHERE LIB_UID='" + TXT + "'
			 */

			String quary = "SELECT COUNT(*) FROM LIB_USERS WHERE LIB_UID='" + id + "'";

			pstmt = tempConn.prepareStatement(quary);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				index = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return index;
	}

	/**
	 * 매개변수 유저 아이디에 해당하는 DB 비밀번호와 입력값이 서로 일치하는지 확인.
	 * 
	 * @param id
	 * @param txtpw
	 * @return true: 일치 | false: 불일치
	 */
	public boolean checkPw(String id, JPasswordField txtpw) {
		String pw = Helper.get_Pass(txtpw);
		String db_pwd = "";
		boolean flag = false;
		try {
			if (tempConn == null)
				tempConn = DBConnManager.getConn("ai", "1234");
			/*
			 * SELECT LIB_PASS FROM LIB_USERS WHERE LIB_UID = '유저 아이디'
			 */

			String query = "SELECT LIB_PASS FROM LIB_USERS WHERE LIB_UID='" + id + "'";

			pstmt = tempConn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				db_pwd = rs.getString(1);
			}

			if (pw.equals(db_pwd))
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs = null;
				if (cs != null)
					cs = null;
				if (stmt != null)
					stmt = null;
				if (pstmt != null)
					pstmt = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	// ------------------------------------------
	// ------------------------------------------
	// ------------------------------------------

}
