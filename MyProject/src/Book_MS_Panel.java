import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

public class Book_MS_Panel extends JPanel {
	private JTable table;
	Check ck=null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	private JLabel[] pageLabel;

	JLabel[] selectedTable = new JLabel[5];
	
	
	public Book_MS_Panel() {
		setSize(1000, 700);
		setLayout(null);
		
		// table 부분
		table = new JTable();
		table.setBounds(305, 192, 375, 16);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(100, 53);
		scrollPane.setSize(500, 500);
		add(scrollPane);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "\uB3C4\uC11C \uBC88\uD638", "\uB3C4\uC11C\uBA85", "\uC800\uC790", "\uCD9C\uD310\uC0AC",
						"\uBC1C\uD589\uC77C" }));

		JButton returnLogin_bk = new JButton("돌아가기");
		returnLogin_bk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyProject.ChangePanel.removeAll();
				MyProject.ChangePanel.add(MyProject.smp);
				MyProject.ChangePanel.repaint();
				MyProject.ChangePanel.revalidate();
			}
		});
		returnLogin_bk.setBounds(800, 600, 97, 23);
		add(returnLogin_bk);

		JLabel lblNewLabel_2 = new JLabel("도서 검색");
		lblNewLabel_2.setBounds(451, 26, 57, 15);
		add(lblNewLabel_2);

		JLabel lb_Book = new JLabel("도서 번호");
		lb_Book.setBounds(623, 60, 57, 15);
		add(lb_Book);

		JLabel lb_Book_1 = new JLabel("도서명");
		lb_Book_1.setBounds(623, 120, 57, 15);
		add(lb_Book_1);

		JLabel lb_Book_2 = new JLabel("저자");
		lb_Book_2.setBounds(623, 180, 57, 15);
		add(lb_Book_2);

		JLabel lb_Book_3 = new JLabel("출판사");
		lb_Book_3.setBounds(623, 240, 57, 15);
		add(lb_Book_3);

		JLabel lb_Book_4 = new JLabel("발행일");
		lb_Book_4.setBounds(623, 300, 57, 15);
		add(lb_Book_4);

		JComboBox cmb_bookSearch = new JComboBox();
		cmb_bookSearch.setModel(new DefaultComboBoxModel(new String[] {"도서명", "저자"}));
		cmb_bookSearch.setMaximumRowCount(2);
		cmb_bookSearch.setBounds(623, 384, 78, 21);
		add(cmb_bookSearch);

		JButton btn_bookSearch = new JButton("검색");
		btn_bookSearch.setBounds(909, 383, 67, 23);
		add(btn_bookSearch);

		makeTable();
		
		txt_bookSearch = new JTextField();
		txt_bookSearch.setBounds(713, 384, 184, 21);
		add(txt_bookSearch);
		txt_bookSearch.setColumns(10);
		
		JButton btn_rental = new JButton("대여");
		btn_rental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookRental br = new BookRental(MyProject.UserId,selectedTable[0].getText());
			}
		});
		btn_rental.setBounds(617, 446, 120, 29);
		add(btn_rental);
		
		JLabel lblNewLabel = new JLabel("대여 여부");
		lblNewLabel.setBounds(623, 346, 57, 15);
		add(lblNewLabel);
		
		JLabel lb_check_rental = new JLabel("");
		lb_check_rental.setBounds(700, 346, 57, 15);
		add(lb_check_rental);
		
		JButton btn_reserve = new JButton("예약");
		btn_reserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserve rs = new Reserve(selectedTable[0].getText());
			}
		});
		btn_reserve.setBounds(769, 449, 120, 29);
		add(btn_reserve);
		//키입력 동적 검색
//		txt_bookSearch.addKeyListener(new KeyAdapter() {
//			public void keyReleased(KeyEvent e){
//				String val = txt_bookSearch.getText();
//				TableRowSorter<TableModel> trs = new TableRowSorter<>(table.getModel());
//				table.setRowSorter(trs);
//				trs.setRowFilter(RowFilter.regexFilter(val));
//			}
//		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				for (int i = 0; i < table.getColumnCount(); i++) {
					selectedTable[i].setText((String) table.getModel().getValueAt(row, i));
				}
				ck=new Check(selectedTable[0].getText());
				lb_check_rental.setText(ck.checkRental());
				repaint();
				revalidate();
			}
		});
		
		btn_bookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmb_bookSearch.getSelectedItem().toString().equals("저자"))
				{
					SearchBook srch=new SearchBook(table,(String)txt_bookSearch.getText(),"author");
				}
				else
				{
					SearchBook srch=new SearchBook(table,(String)txt_bookSearch.getText(),"title");
				}
			}
		});
	}
	
	private void makeTable() {
		int index = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AI", "1234");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.77:1521:xe", "AI", "1234");

			String quary = "select cast((count(*)/10000) as number(4)) from lib_books";

			pstmt = conn.prepareStatement(quary);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				index = rs.getInt(1);
//				index = rs.getInt(1)+1;
			}

		} catch (Exception ex) {
			// TODO: handle exception
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
				// TODO: handle exception
				e2.printStackTrace();
			}

		}

		pageLabel = makePage(index);
		for (JLabel lb : pageLabel)
			add(lb);

		for (int i = 0; i < selectedTable.length; i++) {
			selectedTable[i] = new JLabel("");
			selectedTable[i].setBounds(700, 60 + (60 * i), 200, 15);
			add(selectedTable[i]);

		}
	}

	private JLabel[] makePage(int cnt) {

		JLabel[] arr = new JLabel[cnt];
		for (int i = 0; i < cnt; i++) {
			JLabel mkLabel = new JLabel("" + (i + 1));

			mkLabel.addMouseListener(new MouseAdapter() {
				// 수정필요
				@Override
				public void mouseClicked(MouseEvent e) {
					printTable(mkLabel.getText());
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel lb = (JLabel) e.getSource();
					lb.setForeground(Color.red);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel lb = (JLabel) e.getSource();
					lb.setForeground(Color.black);
				}
			});

			mkLabel.setBounds(100 + (LB_WIDTH * i), 570, LB_WIDTH, LB_HEIGHT);
			arr[i] = mkLabel;
		}
		return arr;
	}

	// test
	private void printTable(String page) {
		SelectAction s_action = new SelectAction(table, Integer.parseInt(page, 10) - 1);
	}
	private static final int LB_WIDTH = 20;
	private static final int LB_HEIGHT = 23;
	private JTextField txt_bookSearch;
}
