package swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Book_MS_Panel extends JPanel {
	// variables(Components)
	private JLabel lbl_book_ms_panel_title;
	private JLabel lbl_bid;
	private JLabel lbl_book_title;
	private JLabel lbl_author;
	private JLabel lbl_copyright;
	private JLabel lbl_year;
	private JLabel lbl_rent_possibility;
	private JComboBox<String> cmb_bookSearch;
	private JButton btn_rental;
	private JLabel lb_check_rental;
	private JButton btn_reserve;

	private JScrollPane scrollPane;
	private JTable table;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public JButton btn_bookSearch;

	private JLabel[] pageLabel;

	JLabel[] selectedTable = new JLabel[5];

	public Book_MS_Panel() {
		setSize(1000, 700);
		setLayout(null);

		// table 부분
		table = new JTable();
		table.setBounds(305, 192, 375, 16);
		scrollPane = new JScrollPane(table);
		scrollPane.setLocation(100, 53);
		scrollPane.setSize(500, 500);
		add(scrollPane);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\uB3C4\uC11C \uBC88\uD638",
				"\uB3C4\uC11C\uBA85", "\uC800\uC790", "\uCD9C\uD310\uC0AC", "\uBC1C\uD589\uC77C" }));

		JButton returnLogin_bk = new JButton("돌아가기");
		returnLogin_bk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyProject.switchTopPanel(MyProject.smp);
			}
		});
		returnLogin_bk.setBounds(800, 600, 97, 23);
		add(returnLogin_bk);

		lbl_book_ms_panel_title = new JLabel("도서 검색");
		lbl_book_ms_panel_title.setBounds(451, 26, 57, 15);
		add(lbl_book_ms_panel_title);

		lbl_bid = new JLabel("도서 번호");
		lbl_bid.setBounds(623, 60, 57, 15);
		add(lbl_bid);

		lbl_book_title = new JLabel("도서명");
		lbl_book_title.setBounds(623, 120, 57, 15);
		add(lbl_book_title);

		lbl_author = new JLabel("저자");
		lbl_author.setBounds(623, 180, 57, 15);
		add(lbl_author);

		lbl_copyright = new JLabel("출판사");
		lbl_copyright.setBounds(623, 240, 57, 15);
		add(lbl_copyright);

		lbl_year = new JLabel("발행일");
		lbl_year.setBounds(623, 300, 57, 15);
		add(lbl_year);

		cmb_bookSearch = new JComboBox<String>();
		cmb_bookSearch.setModel(new DefaultComboBoxModel<String>(new String[] { "도서명", "저자" }));
		cmb_bookSearch.setMaximumRowCount(2);
		cmb_bookSearch.setBounds(623, 384, 78, 21);
		add(cmb_bookSearch);

		btn_bookSearch = new JButton("검색");
		btn_bookSearch.setBounds(909, 383, 67, 23);
		add(btn_bookSearch);

		makeTable();

		txt_bookSearch = new JTextField();
		txt_bookSearch.setBounds(713, 384, 184, 21);
		add(txt_bookSearch);
		txt_bookSearch.setColumns(10);

		btn_rental = new JButton("대여");
		btn_rental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MyProject.dml.rentBook(MyProject.UserId, selectedTable[0].getText());
				JOptionPane.showMessageDialog(null, "대여 완료되었습니다.");
			}
		});

		btn_rental.setBounds(617, 446, 120, 29);
		add(btn_rental);

		lbl_rent_possibility = new JLabel("대여 여부");
		lbl_rent_possibility.setBounds(623, 346, 57, 15);
		add(lbl_rent_possibility);

		lb_check_rental = new JLabel("");
		lb_check_rental.setBounds(700, 346, 57, 15);
		add(lb_check_rental);

		//TODO 예약에서 도서번호를 매개변수로/예약
		btn_reserve = new JButton("예약");
		btn_reserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyProject.dml.reserveBook(selectedTable[0].getText());
				JOptionPane.showMessageDialog(null, "예약 완료되었습니다.");
			}
		});

		btn_reserve.setBounds(769, 449, 120, 29);
		add(btn_reserve);

		btn_bookSearch.setEnabled(false);
		btn_reserve.setEnabled(false);
		btn_rental.setEnabled(false);

		
		//TODO 테이블에서 도서 선택/자세히 보기
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				for (int i = 0; i < table.getColumnCount(); i++) {
					selectedTable[i].setText((String) table.getModel().getValueAt(row, i));
				}
				String result = MyProject.dml.canRent(selectedTable[0].getText());
				lb_check_rental.setText(result);

				btn_bookSearch.setEnabled(true);
				// 예약 중이면 둘다 없애고 예약 가능이면 예약 버튼 활성화 대여 가능이면 대여 버튼 활성화
				String temp = lb_check_rental.getText().trim();

				if (temp.equals("대여가능")) {
					btn_rental.setEnabled(true);
					btn_reserve.setEnabled(false);
				} else if (temp.equals("예약가능")) {
					btn_reserve.setEnabled(true);
					btn_rental.setEnabled(false);
				} else {
					btn_reserve.setEnabled(false);
					btn_rental.setEnabled(false);
				}

				repaint();
				revalidate();
			}
		});

		btn_bookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cmb_bookSearch.getSelectedItem().toString().equals("저자"))
					MyProject.dml.searchBook(table, (String) txt_bookSearch.getText(), "author");
				else
					MyProject.dml.searchBook(table, (String) txt_bookSearch.getText(), "title");
			}
		});
	}
	//TODO 도서목록 1만개 페이지 분할 /21만개
	private void makeTable() {
		int count = MyProject.dml.countAllBooks();
		pageLabel = makePage(count);
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
					btn_bookSearch.setEnabled(true);
					MyProject.dml.selectBooks(table, Integer.parseInt(mkLabel.getText(), 10) - 1);
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

	private static final int LB_WIDTH = 20;
	private static final int LB_HEIGHT = 23;
	private JTextField txt_bookSearch;
}
