import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JTable;



public class Customer {

	public static String u_idx;
	
	public static String[][] getCustomers(){
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT NAME,PHONE,GENDER,AGE,NOTE FROM CUSTOMER");
			ResultSet results = statement.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()){
				list.add(new String[]{
						results.getString("name"),
						results.getString("phone"),
						results.getString("gender"),
						results.getString("age"),
						results.getString("note")
				});
			}
			System.out.println("The data has been fetched");
			String[][] arr = new String[list.size()][5];
			return list.toArray(arr);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	// 테이블에 출력할 책 quary 받기
	public static String[][] getBooks(){	
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT BID,TITLE,AUTHOR,COPYRIGHT,YEAR,LENTABLE FROM LIB_BOOKS");
			ResultSet results = statement.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()){
				list.add(new String[]{
						results.getString("BID"),
						results.getString("TITLE"),
						results.getString("AUTHOR"),
						results.getString("COPYRIGHT"),
						results.getString("YEAR"),
						results.getString("LENTABLE")
				});
			}
			System.out.println("The data has been fetched");
			String[][] arr = new String[list.size()][6];
			return list.toArray(arr);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}
	
	//과거 본인대여상황
	public static String[][] lib_prnt_pst(){	
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT BID,TITLE,AUTHOR,LENTDATE,RETDATE,LIB_UID "
					
					+ "FROM LIB_PRNT_PST");
			
//			+ "FROM LIB_PRNT_PST WHERE LIB_UID=?");
//			statement.setString(1, u_idx);
			
			ResultSet results = statement.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()){
				list.add(new String[]{
						results.getString("BID"),
						results.getString("TITLE"),
						results.getString("AUTHOR"),
						results.getString("LENTDATE"),
						results.getString("RETDATE"),
						results.getString("LIB_UID")
				});
			}
			System.out.println("The data has been fetched");
			String[][] arr = new String[list.size()][6];
			return list.toArray(arr);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}
	
	//현재 본인대여상황
	public static String[][] lib_prnt_now(){	
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT BID,TITLE,AUTHOR,LENTDATE,RETDATE,LIB_UID "
					
					+ "FROM LIB_PRNT_NOW");
			
//					+ "FROM LIB_PRNT_NOW WHERE LIB_UID = ?");
//			statement.setString(1, u_idx);
			
			ResultSet results = statement.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()){
				list.add(new String[]{
						results.getString("BID"),
						results.getString("TITLE"),
						results.getString("AUTHOR"),
						results.getString("LENTDATE"),
						results.getString("RETDATE"),
						results.getString("LIB_UID")
				});
			}
			System.out.println("The data has been fetched");
			String[][] arr = new String[list.size()][6];
			return list.toArray(arr);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}
	
	//현재 예약상황
	public static String[][] lib_prnt_rsv(){	
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT BID,TITLE,AUTHOR,RETDATE,LIB_UID "
					
					+ "FROM LIB_PRNT_RENT");
			
//					+ "FROM LIB_PRNT_RENT WHERE LIB_UID = ?");
//			statement.setString(1, u_idx);
			
			ResultSet results = statement.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()){
				list.add(new String[]{
						results.getString("BID"),
						results.getString("TITLE"),
						results.getString("AUTHOR"),
						results.getString("RETDATE"),
						results.getString("LIB_UID")
				});
			}
			System.out.println("The data has been fetched");
			String[][] arr = new String[list.size()][5];
			return list.toArray(arr);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}
	
	public static void createCustomer(String idx,String pass,String name,String phone){
		try {
			Connection con = getConnection();
			PreparedStatement insert = con.prepareStatement(""
					+ "INSERT INTO LIB_USERS"
					+ "(LIB_UID,LIB_PASS,LIB_NAME,LIB_PHONE) "
					+ "VALUES(?,?,?,?)");
			insert.setString(1, idx);
			insert.setString(2, pass);
			insert.setString(3, name);
			insert.setString(4, phone);

			insert.executeUpdate();
			
			System.out.println("The data has been saved");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public static Connection getConnection(){
		try {
			String driver ="oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "ai"; String pass ="1234";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,pass);
			System.out.println("Connection Success");
			return con;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
				
	}
	
	public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
	        double... percentages) {
	    double total = 0;
	    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	        total += percentages[i];
	    }
	 
	    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	        javax.swing.table.TableColumn column = table.getColumnModel().getColumn(i);
	        column.setPreferredWidth((int)
	                (tablePreferredWidth * (percentages[i] / total)));
	    }
	}

}
