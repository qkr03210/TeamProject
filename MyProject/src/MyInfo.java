import javax.swing.JPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyInfo extends JPanel{
//	String id;
	private JTable table = new JTable();
	JScrollPane scrollPane;
	
	   //시간용
	   SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MyInfo(){
		setSize(790, 648);
//		this.id=id;
		setLayout(null);
		
		
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"\uB300\uC5EC\uBC88\uD638", "\uB300\uC5EC\uC77C", "\uBC18\uB0A9\uC77C", "\uBC18\uB0A9\uC608\uC815\uC77C", "\uB3C4\uC11C\uBA85"
			}
		));
		table.setBounds(41, 315, 1, 1);

//		select r.ren_num,r.ren_date,r.rtn_date,r.pre_date, bb.title from lib_rental r, lib_books bb  where r.ren_bid=bb.bid and r.ren_user=MyProject.;

//		UPDATE lib_rental SET rtn_date=TO_DATE(SYSDATE) WHERE ren_num=2;
		
		JLabel selectedTable = new JLabel("");
		selectedTable.setBounds(243, 25, 57, 15);
//		add(selectedTable);
		
		scrollPane = new JScrollPane(table);
		

		
		scrollPane.setBounds(12, 57, 622, 405);
		add(scrollPane);
		
		JButton btn_return = new JButton("반납");
		btn_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					  String format_time1 = format1.format (System.currentTimeMillis());
				         java.sql.Timestamp t = java.sql.Timestamp.valueOf(format_time1);
				         
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");
					System.out.println(selectedTable.getText());
					pstmt = conn.prepareStatement("UPDATE lib_rental SET rtn_date='"+ t+"' WHERE ren_num="+selectedTable.getText());
					pstmt.executeQuery();
					repaint();
					revalidate();
				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {
					try {
						if (rs != null)	rs.close();
						if (pstmt != null) pstmt.close();
						if (conn != null) conn.close();
					} catch (Exception closee) {
					}
				}
				nowRental();
			}
		});
		btn_return.setBounds(657, 54, 97, 23);
		add(btn_return);
		
		JButton btnNewButton = new JButton("전체 대여 내역");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllRental();
			}
		});
		btnNewButton.setBounds(12, 24, 125, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("현재 대여 내역");
		
		btnNewButton_1.setBounds(149, 24, 125, 23);
		add(btnNewButton_1);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				selectedTable.setText((String) table.getModel().getValueAt(row, 0));
				System.out.println(selectedTable.getText());
				repaint();
				revalidate();
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nowRental();
			}
		});
		
		
		
		
	}
	
	public void AllRental(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");
			pstmt = conn.prepareStatement("select r.ren_num,r.ren_date,r.rtn_date,r.pre_date, bb.title from lib_rental r, lib_books bb  where r.ren_bid=bb.bid and r.ren_user='"+MyProject.UserId+"'");
			rs=pstmt.executeQuery();
			
			String str[] = new String[5];
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			while(rs.next())
			{
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
				str[2]=rs.getString(3);
				str[3]=rs.getString(4);
				str[4]=rs.getString(5);
				
				model = (DefaultTableModel) table.getModel();
				model.addRow(str);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (rs != null)	rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception closee) {
			}
		}
	}
	public void nowRental(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");
			
//			pstmt = conn.prepareStatement("select r.ren_num,r.ren_date,r.rtn_date,r.pre_date, bb.title from lib_rental r, lib_books bb  where r.ren_bid=bb.bid and r.ren_user='"+MyProject.UserId+"'");
			pstmt = conn.prepareStatement("select r.ren_num,r.ren_date,r.rtn_date,r.pre_date, bb.title from lib_rental r, lib_books bb  where r.ren_bid=bb.bid and r.ren_user='"+MyProject.UserId+"'and r.rtn_date is null");
			rs=pstmt.executeQuery();
			
			String str[] = new String[5];
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			while(rs.next())
			{
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
				str[2]=rs.getString(3);
				str[3]=rs.getString(4);
				str[4]=rs.getString(5);
				
				model = (DefaultTableModel) table.getModel();
				model.addRow(str);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (rs != null)	rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception closee) {
			}
		}
	}
}
