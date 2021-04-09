package swing;

import javax.swing.JPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyInfo extends JPanel {
//	String id;

	private JScrollPane scrpnl_rent;
	private JTable table_rent = new JTable();
	private JScrollPane scrpnl_reserve;
	private JTable table_reserve = new JTable();
	private JButton btn_return;
	private JButton btn_all_rent_history;
	private JButton btn_current_rent_history;
	private JButton btn_reserve_history;
	private JLabel selectedRentNum;

	// 시간용
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public MyInfo() {
		setSize(790, 648);
		setLayout(null);

		table_rent.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\uB300\uC5EC\uBC88\uD638",
				"\uB300\uC5EC\uC77C", "\uBC18\uB0A9\uC77C", "\uBC18\uB0A9\uC608\uC815\uC77C", "\uB3C4\uC11C\uBA85" }));
		table_reserve.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\uB3C4\uC11C \uBC88\uD638", "\uB3C4\uC11C\uBA85", "\uC800\uC790" }));
		table_rent.setBounds(41, 315, 1, 1);

		selectedRentNum = new JLabel("");
		selectedRentNum.setBounds(243, 25, 57, 15);

		scrpnl_rent = new JScrollPane(table_rent);
		scrpnl_reserve = new JScrollPane(table_reserve);

		scrpnl_rent.setBounds(12, 57, 622, 405);
		add(scrpnl_rent);

		btn_return = new JButton("반납");
		btn_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String format_time1 = format1.format(System.currentTimeMillis());
					java.sql.Timestamp t = java.sql.Timestamp.valueOf(format_time1);

					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

					// 반납 null 및 예약 테이블일때 반납 버튼 사라짐 (민성)
					if (t.equals("") || selectedRentNum.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "책을 선택해 주십시오");
						return;
					}
					pstmt = conn.prepareStatement(
							"UPDATE lib_rental SET rtn_date='" + t + "' WHERE ren_num=" + selectedRentNum.getText());
					pstmt.executeQuery();
					repaint();
					revalidate();
				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (pstmt != null)
							pstmt.close();
						if (conn != null)
							conn.close();
					} catch (Exception closee) {
					}
				}
				nowRental();
			}
		});

		scrpnl_reserve.setBounds(12, 57, 622, 405);
		add(scrpnl_reserve);
		scrpnl_reserve.setVisible(false);

		table_reserve.getColumnModel().getColumn(0).setPreferredWidth(150);
		table_reserve.getColumnModel().getColumn(1).setPreferredWidth(389);
		table_reserve.setBounds(0, 0, 1, 1);

		btn_return.setBounds(657, 54, 97, 23);
		add(btn_return);

		btn_all_rent_history = new JButton("전체 대여 내역");
		btn_all_rent_history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_return.setVisible(false);// 전체내역에서는 반납 버튼이 활성화 될 필요가 없지 않을까요?
				scrpnl_rent.setVisible(true);
				scrpnl_reserve.setVisible(false);
				AllRental();
			}
		});
		btn_all_rent_history.setBounds(12, 24, 125, 23);
		add(btn_all_rent_history);

		btn_current_rent_history = new JButton("현재 대여 내역");

		btn_current_rent_history.setBounds(149, 24, 125, 23);
		add(btn_current_rent_history);

		btn_reserve_history = new JButton("예약 내역");
		btn_reserve_history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn_return.setVisible(false);
				scrpnl_rent.setVisible(false);
				scrpnl_reserve.setVisible(true);
				myReserv();
			}
		});
		btn_reserve_history.setBounds(286, 24, 125, 23);
		add(btn_reserve_history);

		table_rent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table_rent.getSelectedRow();
				selectedRentNum.setText((String) table_rent.getModel().getValueAt(row, 0));
				System.out.println("대여번호 " + selectedRentNum.getText());
				repaint();
				revalidate();
			}
		});

		btn_current_rent_history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_return.setVisible(true);
				scrpnl_rent.setVisible(true);
				scrpnl_reserve.setVisible(false);
				nowRental();
			}
		});

	}

	public void AllRental() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");
			pstmt = conn.prepareStatement(
					"select r.ren_num,r.ren_date,r.rtn_date,r.pre_date, bb.title from lib_rental r, lib_books bb  where r.ren_bid=bb.bid and r.ren_user='"
							+ MyProject.UserId + "'");
			rs = pstmt.executeQuery();

			String str[] = new String[5];
			DefaultTableModel model = (DefaultTableModel) table_rent.getModel();
			model.setNumRows(0);
			while (rs.next()) {
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				str[4] = rs.getString(5);

				model = (DefaultTableModel) table_rent.getModel();
				model.addRow(str);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception closee) {
			}
		}
	}

	public void nowRental() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

			pstmt = conn.prepareStatement(
					"select r.ren_num,r.ren_date,r.rtn_date,r.pre_date, bb.title from lib_rental r, lib_books bb  where r.ren_bid=bb.bid and r.ren_user='"
							+ MyProject.UserId + "'and r.rtn_date is null");
			rs = pstmt.executeQuery();

			String str[] = new String[5];
			DefaultTableModel model = (DefaultTableModel) table_rent.getModel();
			model.setNumRows(0);
			while (rs.next()) {
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = rs.getString(4);
				str[4] = rs.getString(5);

				model = (DefaultTableModel) table_rent.getModel();
				model.addRow(str);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception closee) {
			}
		}
	}

	// 21-04-07 박상준 작업중
	public void myReserv() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");
			pstmt = conn.prepareStatement(
					"select r.ren_bid,bb.title,bb.author from lib_rental r, lib_books bb  where r.ren_bid=bb.bid and r.sun_id='"
							+ MyProject.UserId + "'");
			rs = pstmt.executeQuery();

			String str[] = new String[5];
			DefaultTableModel model = (DefaultTableModel) table_reserve.getModel();
			model.setNumRows(0);
			while (rs.next()) {
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);

				model = (DefaultTableModel) table_reserve.getModel();
				model.addRow(str);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception closee) {
			}
		}
	}
}
