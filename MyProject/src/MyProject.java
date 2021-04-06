import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MyProject extends JFrame {
	//판단용 변수
	private static int checkId = -1;
	public static String UserId="";
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	private JTable table;

	public static JLayeredPane ChangePanel;

	private JPanel FindPwd;
	public static JPanel LoginPanel;
	private JPanel MainPanel;
	private JPanel UserPanel;
	private JPanel Book_MS_Panel;
	private JPanel Reserv_Panel;
	public static SelectMenuPanel smp=new SelectMenuPanel();
	private JLabel label;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lb_ID;
	private JLabel lb_PWD;
	private JLabel lb_rePWD;
	private JLabel lb_NAME;
	private JLabel lb_PHONENUMBER;
	private JLabel lb_Book;
	private JLabel lb_Book_1;
	private JLabel lb_Book_2;
	private JLabel lb_Book_3;
	private JLabel lb_Book_4;
	
	private JButton btnNewButton_4;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btn_Reserv;
	private JButton returnLogin;
	private JButton btn_Reserv_ms;
	private JButton returnLogin_bk;
	private JButton btn_idCheck;
	private JButton addUserBtn;
	private JButton btn_bookSearch;
	
	private JComboBox cmb_bookSearch;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	private JLabel[] pageLabel;

	JLabel[] selectedTable = new JLabel[5];
	

	public MyProject() {
		initaialize();
//		makeTable();
		setEvent();
	}

	private void initaialize() {

		getContentPane().setLayout(null);

//		FindPwd = new JPanel();
//		FindPwd.setBounds(0, 0, 1000, 700);
//		// getContentPane().add(FindPwd);
//		FindPwd.setLayout(null);
//
//		lblNewLabel_3 = new JLabel("이름");
//		lblNewLabel_3.setBounds(312, 220, 57, 15);
//		FindPwd.add(lblNewLabel_3);
//
//		textField_7 = new JTextField();
//		textField_7.setBounds(401, 217, 116, 21);
//		FindPwd.add(textField_7);
//		textField_7.setColumns(10);
//
//		lblNewLabel_4 = new JLabel("ID");
//		lblNewLabel_4.setBounds(312, 265, 57, 15);
//		FindPwd.add(lblNewLabel_4);
//
//		textField_8 = new JTextField();
//		textField_8.setBounds(401, 262, 116, 21);
//		FindPwd.add(textField_8);
//		textField_8.setColumns(10);
//
//		btnNewButton_4 = new JButton("비밀번호 찾기");
//
//		btnNewButton_4.setBounds(357, 383, 160, 23);
//		FindPwd.add(btnNewButton_4);
//
//		lblNewLabel_5 = new JLabel("연락처");
//		lblNewLabel_5.setBounds(312, 315, 57, 15);
//		FindPwd.add(lblNewLabel_5);
//
//		textField_9 = new JTextField();
//		textField_9.setBounds(401, 312, 116, 21);
//		FindPwd.add(textField_9);
//		textField_9.setColumns(10);

		ChangePanel = new JLayeredPane();
		ChangePanel.setBounds(0, 0, 1000, 700);
		getContentPane().add(ChangePanel);
		
		LoginPanel lp=new LoginPanel();
		ChangePanel.add(lp);
		
//		LoginPanel = new JPanel();
//		LoginPanel.setBounds(0, 0, 1000, 700);
//
//		ChangePanel.add(LoginPanel);
//		LoginPanel.setLayout(null);
//
//		textField = new JTextField();
//		textField.setBounds(383, 258, 190, 21);
//		LoginPanel.add(textField);
//		textField.setColumns(10);
//
//		textField_1 = new JTextField();
//		textField_1.setBounds(383, 288, 190, 21);
//		LoginPanel.add(textField_1);
//		textField_1.setColumns(10);
//
//		label = new JLabel("로그인");
//		label.setBounds(445, 227, 45, 21);
//		LoginPanel.add(label);
//		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
//
//		lblNewLabel = new JLabel("회원 가입");
//		lblNewLabel.setBounds(383, 319, 76, 15);
//		LoginPanel.add(lblNewLabel);
//
//		lblNewLabel_1 = new JLabel("비밀번호 찾기");
//
//		lblNewLabel_1.setBounds(493, 319, 102, 15);
//		LoginPanel.add(lblNewLabel_1);
//
//		btnNewButton = new JButton("로그인");
//
//		btnNewButton.setBounds(383, 355, 190, 30);
//		LoginPanel.add(btnNewButton);

		
		MainPanel = new JPanel();
		MainPanel.setBounds(0, 0, 1000, 700);
		MainPanel.setLayout(null);

		
		btnNewButton_1 = new JButton("도서 검색");
		btnNewButton_1.setBounds(561, 78, 97, 23);
		MainPanel.add(btnNewButton_1);

		btn_Reserv = new JButton("예약 하기");
		btn_Reserv.setBounds(664, 78, 97, 23);
		MainPanel.add(btn_Reserv);

		Reserv_Panel = new JPanel();
		Reserv_Panel.setBounds(0, 0, 1000, 700);
		// ChangePanel.add(Reserv_Panel);
		Reserv_Panel.setLayout(null);

		returnLogin = new JButton("시작화면으로");
		returnLogin.setBounds(664, 78, 97, 23);
		Reserv_Panel.add(returnLogin);

		btn_Reserv_ms = new JButton("예약 관리");
		btn_Reserv_ms.setBounds(664, 101, 97, 23);
		MainPanel.add(btn_Reserv_ms);

		this.setSize(1000, 700);
		this.setVisible(true);
		this.setLocation(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setEvent() {

		btn_Reserv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePanel.removeAll();
				ChangePanel.add(Reserv_Panel);
				ChangePanel.repaint();
				ChangePanel.revalidate();
			}
		});
		returnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePanel.removeAll();
				ChangePanel.add(LoginPanel);
				ChangePanel.repaint();
				ChangePanel.revalidate();
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePanel.removeAll();
				ChangePanel.add(Book_MS_Panel);
				ChangePanel.repaint();
				ChangePanel.revalidate();
			}
		});

		
		btn_Reserv_ms.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
				ChangePanel.removeAll();
				ChangePanel.add(smp);
				ChangePanel.repaint();
				ChangePanel.revalidate();
				
			}
		});
	}


	public static void main(String[] args) {
		MyProject window = new MyProject();
	}

}
