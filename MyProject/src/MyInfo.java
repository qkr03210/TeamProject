import javax.swing.JPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyInfo extends JPanel{
//	String id;
	private JTable table = new JTable();
	JScrollPane scrollPane;
	
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
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 10, 622, 405);
		add(scrollPane);
		
		JButton btn_return = new JButton("반납");
		btn_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_return.setBounds(659, 25, 97, 23);
		add(btn_return);
		

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
		} catch (Exception e) {
			e.printStackTrace();
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
