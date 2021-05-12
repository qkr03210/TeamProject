import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javafx.scene.control.TableColumn;

import java.awt.Color;



public class Lib_App {
	
	private JFrame frame;
	private JPasswordField userpass;
	private JTextField userid;
	private JLabel lblId;
	private JLabel lblPassword;
	private JPanel mainPanel;
	private JTextField name;
	private JTextField phone;
	private JTextField idx;
	private JPasswordField pass1;
	private JPasswordField pass2;
	private JTextField search;
	private JTextField srch;
	private JTextField reservbookidtxt;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	
	
	public static String u_id;
	
	Connection con=Customer.getConnection();
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lib_App window = new Lib_App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Lib_App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Customer customer = new Customer();
		
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
	
		JPanel bookpan = new JPanel();
		bookpan.setBounds(0, 0, 794, 550);
		frame.getContentPane().add(bookpan);
		bookpan.setLayout(null);
		frame.getContentPane().setLayout(null);
		bookpan.setVisible(false);
		
		ImagePanel bookimgPanel = new ImagePanel(new ImageIcon("G:/Java_Work/Lib_App/Image/Welcome.jpg").getImage());
		bookimgPanel.setBounds(0, 0, 794, 550);
		frame.setSize( bookimgPanel.getWidth(), bookimgPanel.getHeight());
		bookpan.add(bookimgPanel);
		bookimgPanel.setLayout(null);
		
		String[][] bookdata = customer.getBooks();
		String[] bookheaders = new String[]{"BookID","Title","Author","Copyright","Year","Lentable"};
		JTable booktable = new JTable(bookdata,bookheaders);
		booktable.setLocation(30, 70);
		booktable.setSize(560, 400);
		booktable.setRowHeight(25);
		booktable.setFont(new Font("Dialog", Font.PLAIN, 13));
		booktable.setAlignmentX(0);
		booktable.setPreferredScrollableViewportSize(new Dimension(560,400));		
		JScrollPane scrollPane = new JScrollPane(booktable);
		scrollPane.setLocation(27, 98);
		scrollPane.setSize(739, 375);
		bookimgPanel.add(scrollPane);
		
		TableColumnModel columModels = booktable.getColumnModel();
		customer.setJTableColumnsWidth(booktable, 560, 12, 62, 12, 8, 3,3);
		
		JLabel lblBooks = new JLabel("Books");
		lblBooks.setBounds(257, 10, 309, 39);
		lblBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblBooks.setForeground(java.awt.Color.ORANGE);
		lblBooks.setFont(new Font("Arno Pro", Font.BOLD, 25));
		bookimgPanel.add(lblBooks);
		
		srch = new JTextField();
		srch.setFont(new Font("굴림", Font.BOLD, 15));
		srch.setBounds(133, 50, 328, 32);
		bookimgPanel.add(srch);
		srch.setColumns(10);
		srch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				String val = srch.getText();
				TableRowSorter<TableModel> trs = new TableRowSorter<>(booktable.getModel());
				booktable.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(val));
			}
		});
	
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setForeground(Color.ORANGE);
		lblSearch.setFont(new Font("Arno Pro", Font.BOLD, 25));
		lblSearch.setBounds(38, 50, 83, 39);
		bookimgPanel.add(lblSearch);
		
		JButton gotomyinfo = new JButton("");
		gotomyinfo.setIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\gotomyinfo.jpg"));
		gotomyinfo.setPressedIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\gotomyinfo_clicked.jpg"));
		gotomyinfo.setBackground(Color.ORANGE);
		gotomyinfo.setBounds(597, 488, 169, 39);
		bookimgPanel.add(gotomyinfo);
		gotomyinfo.setFont(new Font("Adobe Fangsong Std R", Font.BOLD, 16));
		
		reservbookidtxt = new JTextField();
		reservbookidtxt.setHorizontalAlignment(SwingConstants.CENTER);
		reservbookidtxt.setToolTipText("");
		reservbookidtxt.setFont(new Font("굴림", Font.PLAIN, 13));
		reservbookidtxt.setColumns(10);
		reservbookidtxt.setBounds(48, 483, 141, 32);
		reservbookidtxt.setText("대여예약도서ID 입력");
		bookimgPanel.add(reservbookidtxt);
		
		JButton rntbookid = new JButton("");
		rntbookid.setIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Commit.jpg"));
		rntbookid.setPressedIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Commit_clicked.jpg"));
		rntbookid.setFont(new Font("Adobe Fangsong Std R", Font.BOLD, 16));
		rntbookid.setBackground(Color.ORANGE);
		rntbookid.setBounds(201, 483, 83, 32);
		bookimgPanel.add(rntbookid);
		ImagePanel welcomePanel = new ImagePanel(new ImageIcon("G:/Java_Work/Lib_App/Image/Welcome.jpg").getImage());
		welcomePanel.setBounds(0, 0, 794, 550);
		frame.setSize(welcomePanel.getWidth(),welcomePanel.getHeight());
		frame.getContentPane().add(welcomePanel);		
		
			JLabel lblLogIn = new JLabel("Log In");
			lblLogIn.setForeground(java.awt.Color.WHITE);
			lblLogIn.setFont(new Font("Arno Pro Display", Font.BOLD, 30));
			lblLogIn.setBounds(355, 173, 185, 45);
			welcomePanel.add(lblLogIn);
			
			userid = new JTextField();
			userid.setFont(new Font("굴림", Font.BOLD, 16));
			userid.setText("Enter ID");
			userid.setBounds(355, 217, 160, 30);
			welcomePanel.add(userid);
			userid.setColumns(10);
			
			userpass = new JPasswordField();
			userpass.setBounds(355, 258, 160, 30);
			welcomePanel.add(userpass);
			
			lblId = new JLabel("ID:");
			lblId.setHorizontalAlignment(SwingConstants.RIGHT);
			lblId.setFont(new Font("Arno Pro", Font.PLAIN, 25));
			lblId.setForeground(java.awt.Color.WHITE);
			lblId.setBounds(270, 218, 80, 31);
			welcomePanel.add(lblId);
			
			lblPassword = new JLabel("Password:");
			lblPassword.setForeground(java.awt.Color.WHITE);
			lblPassword.setFont(new Font("Arno Pro", Font.PLAIN, 25));
			lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPassword.setBounds(224, 258, 125, 31);
			
			
			JButton btnLogIn = new JButton("");
			btnLogIn.setIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Login.jpg"));
			btnLogIn.setPressedIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Login_clicked.jpg"));
			btnLogIn.setFont(new Font("Adobe Fangsong Std R", Font.PLAIN, 14));
			btnLogIn.setBounds(355, 299, 160, 30);
			welcomePanel.add(btnLogIn);
			welcomePanel.add(lblPassword);
			
			btnLogIn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				String login ="SELECT LIB_UID,LIB_PASS FROM LIB_USERS WHERE LIB_UID=? AND LIB_PASS=?";
				try {
					u_id = userid.getText();
					Customer.u_idx = u_id;
					ps=con.prepareStatement(login);
//					ps.setString(1, userid.getText());
					ps.setString(1, u_id);
					ps.setString(2, userpass.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
						welcomePanel.setVisible(false);
						bookpan.setVisible(true);		
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Login Fail");
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
				
		JButton newaccount = new JButton("");
		newaccount.setIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Newac.jpg"));
		newaccount.setPressedIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Newac_clicked.jpg"));
		newaccount.setFont(new Font("Adobe Fangsong Std R", Font.PLAIN, 14));
		newaccount.setBounds(607, 20, 160, 30);
		welcomePanel.add(newaccount);
		
		JPanel myinfo = new JPanel();
		myinfo.setBounds(0, 0, 794, 550);
		frame.getContentPane().add(myinfo);
		myinfo.setLayout(null);
		myinfo.setVisible(false);
		
		ImagePanel myinfoimgPanel = new ImagePanel(new ImageIcon("G:/Java_Work/Lib_App/Image/Welcome.jpg").getImage());
		myinfoimgPanel.setBounds(0, 0, 794, 550);
		frame.setSize( myinfoimgPanel.getWidth(), myinfoimgPanel.getHeight());
		myinfo.add(myinfoimgPanel);
		myinfoimgPanel.setLayout(null);
		
		JLabel lblMyInformation = new JLabel("My Information");
		lblMyInformation.setBounds(253, 20, 309, 39);
		lblMyInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyInformation.setForeground(Color.ORANGE);
		lblMyInformation.setFont(new Font("Arno Pro", Font.BOLD, 25));
		myinfoimgPanel.add(lblMyInformation);
		
		JLabel lblName_1 = new JLabel("I D :");
		lblName_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName_1.setForeground(Color.WHITE);
		lblName_1.setFont(new Font("Arno Pro", Font.BOLD, 22));
		lblName_1.setBounds(25, 69, 112, 39);
		myinfoimgPanel.add(lblName_1);
		
		JLabel label = new JLabel("Name :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arno Pro", Font.BOLD, 22));
		label.setBounds(25, 119, 112, 39);
		myinfoimgPanel.add(label);
		
		JLabel lblPhone_1 = new JLabel("Phone :");
		lblPhone_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone_1.setForeground(Color.WHITE);
		lblPhone_1.setFont(new Font("Arno Pro", Font.BOLD, 22));
		lblPhone_1.setBounds(25, 170, 112, 39);
		myinfoimgPanel.add(lblPhone_1);
		
		JButton gotobooks = new JButton("");
		gotobooks.setIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Books.jpg"));
		gotobooks.setPressedIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Books_clicked.jpg"));
		gotobooks.setFont(new Font("Adobe Fangsong Std R", Font.BOLD, 16));
		gotobooks.setBackground(Color.ORANGE);
		gotobooks.setBounds(47, 482, 169, 39);
		myinfoimgPanel.add(gotobooks);
		
		JButton buttonup = new JButton("");
		buttonup.setIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Update.jpg"));
		buttonup.setPressedIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Update_clicked.jpg"));
		buttonup.setFont(new Font("Adobe Fangsong Std R", Font.BOLD, 16));
		buttonup.setBackground(Color.ORANGE);
		buttonup.setBounds(517, 119, 169, 39);
		myinfoimgPanel.add(buttonup);
		
		textField = new JTextField();
		textField.setBounds(149, 69, 190, 42);
		myinfoimgPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(149, 119, 190, 42);
		myinfoimgPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(149, 170, 190, 42);
		myinfoimgPanel.add(textField_2);
		
		gotomyinfo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
					bookpan.setVisible(false);
					myinfo.setVisible(true);
			}
		});
		
		gotobooks.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
					bookpan.setVisible(true);
					myinfo.setVisible(false);
			}
		});
			
		
		String[][] prnt_now = customer.lib_prnt_now();
		String[] prnt_now_head = new String[]{"도서ID","도서제목","도서저자","도서대여일","도서반납예정일","도서대여자ID"};

		JTable prnt_now_tbl = new JTable(prnt_now,prnt_now_head);
		prnt_now_tbl.setLocation(30, 70);
		prnt_now_tbl.setSize(560, 400);
		prnt_now_tbl.setRowHeight(25);
		prnt_now_tbl.setFont(new Font("Dialog", Font.PLAIN, 13));
		prnt_now_tbl.setAlignmentX(0);
		prnt_now_tbl.setPreferredScrollableViewportSize(new Dimension(560,400));		
		JScrollPane scrollPane_now = new JScrollPane(prnt_now_tbl);
		scrollPane_now.setLocation(29, 257);
		scrollPane_now.setSize(740, 166);
		myinfoimgPanel.add(scrollPane_now);
		
		TableColumnModel columModels_now = prnt_now_tbl.getColumnModel();
		customer.setJTableColumnsWidth(prnt_now_tbl, 740, 11, 45, 10, 10, 12,12);
		
		
		String[][] prnt_pst = customer.lib_prnt_pst();
		String[] prnt_pst_head = new String[]{"도서ID","도서제목","도서저자","도서대여일","도서반납일","도서대여자ID"};
		
		JTable prnt_pst_tbl = new JTable(prnt_pst,prnt_pst_head);
		prnt_pst_tbl.setLocation(30, 70);
		prnt_pst_tbl.setSize(560, 400);
		prnt_pst_tbl.setRowHeight(25);
		prnt_pst_tbl.setFont(new Font("Dialog", Font.PLAIN, 13));
		prnt_pst_tbl.setAlignmentX(0);
		prnt_pst_tbl.setPreferredScrollableViewportSize(new Dimension(560,400));
		prnt_pst_tbl.setVisible(false);
		JScrollPane scrollPane_pst = new JScrollPane(prnt_pst_tbl);
		scrollPane_pst.setLocation(29, 257);
		scrollPane_pst.setSize(740, 166);
		myinfoimgPanel.add(scrollPane_pst);
		
		TableColumnModel columModels_pst = prnt_pst_tbl.getColumnModel();
		customer.setJTableColumnsWidth(prnt_pst_tbl, 740, 12, 47, 10, 11, 11,13);

		
		String[][] prnt_rsv = customer.lib_prnt_rsv();
		String[] prnt_rsv_head = new String[]{"도서ID","도서제목","도서저자","도서예약상황","도서예약자ID"};
		
		JTable prnt_rsv_tbl = new JTable(prnt_rsv,prnt_rsv_head);
		prnt_rsv_tbl.setLocation(30, 70);
		prnt_rsv_tbl.setSize(560, 400);
		prnt_rsv_tbl.setRowHeight(25);
		prnt_rsv_tbl.setFont(new Font("Dialog", Font.PLAIN, 13));
		prnt_rsv_tbl.setAlignmentX(0);
		prnt_rsv_tbl.setPreferredScrollableViewportSize(new Dimension(560,400));
		prnt_rsv_tbl.setVisible(false);
		JScrollPane scrollPane_rsv = new JScrollPane(prnt_rsv_tbl);
		scrollPane_rsv.setLocation(29, 257);
		scrollPane_rsv.setSize(740, 166);
		myinfoimgPanel.add(scrollPane_rsv);
		
		TableColumnModel columModels_rsv = prnt_rsv_tbl.getColumnModel();
		customer.setJTableColumnsWidth(prnt_rsv_tbl, 740, 12, 52, 12, 12, 12);
		
		JButton lentnowbtn = new JButton("");
		lentnowbtn.setIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\My_lent.jpg"));
		lentnowbtn.setPressedIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\My_lent_clicked.jpg"));
		lentnowbtn.setFont(new Font("Adobe Fangsong Std R", Font.BOLD, 16));
		lentnowbtn.setBackground(Color.ORANGE);
		lentnowbtn.setBounds(461, 433, 90, 32);
		myinfoimgPanel.add(lentnowbtn);
			
		JButton lentpstbtn = new JButton("");
		lentpstbtn.setIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Pastlent.jpg"));
		lentpstbtn.setPressedIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Pastlent_clicked.jpg"));
		lentpstbtn.setToolTipText("");
		lentpstbtn.setFont(new Font("Adobe Fangsong Std R", Font.BOLD, 16));
		lentpstbtn.setBackground(Color.ORANGE);
		lentpstbtn.setBounds(665, 433, 90, 32);
		myinfoimgPanel.add(lentpstbtn);
		
		JButton reservebtn = new JButton("");
		reservebtn.setIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Reserve.jpg"));
		reservebtn.setPressedIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Reserve_clicked.jpg"));
		reservebtn.setFont(new Font("Adobe Fangsong Std R", Font.BOLD, 16));
		reservebtn.setBackground(Color.ORANGE);
		reservebtn.setBounds(563, 433, 90, 32);
		myinfoimgPanel.add(reservebtn);
		
		lentnowbtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
					customer.lib_prnt_now();
					scrollPane_now.setVisible(true);
					scrollPane_pst.setVisible(false);
					scrollPane_rsv.setVisible(false);
					prnt_now_tbl.setVisible(true);
					prnt_pst_tbl.setVisible(false);
					prnt_rsv_tbl.setVisible(false);

			}
		});
		
		lentpstbtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
					customer.lib_prnt_pst();
					scrollPane_now.setVisible(false);
					scrollPane_pst.setVisible(true);
					scrollPane_rsv.setVisible(false);
					prnt_pst_tbl.setVisible(true);
					prnt_now_tbl.setVisible(false);
					prnt_rsv_tbl.setVisible(false);
			}
		});
		
		reservebtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
					customer.lib_prnt_rsv();
					scrollPane_now.setVisible(false);
					scrollPane_pst.setVisible(false);
					scrollPane_rsv.setVisible(true);
					prnt_rsv_tbl.setVisible(true);
					prnt_now_tbl.setVisible(false);
					prnt_pst_tbl.setVisible(false);
					
			}
		});
		rntbookid.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				String insertrent ="begin p_lentbook(?,?);end;"; 
				try {
					ps=con.prepareStatement(insertrent);
					ps.setString(1, reservbookidtxt.getText());
					ps.setString(2, u_id);
					rs=ps.executeQuery();
					JOptionPane.showMessageDialog(null,"대여 혹은 예약되었습니다.");
					

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		
		ImagePanel newacc = new ImagePanel(new ImageIcon("G:/Java_Work/Lib_App/Image/Welcome.jpg").getImage());
		newacc.setBackground(java.awt.Color.WHITE);
		newacc.setBounds(0, 0, 794, 550);
		frame.getContentPane().add(newacc);
		newacc.setLayout(null);
		newacc.setVisible(false);
		
		JLabel lblWelcomeThisIs = new JLabel("New Account");
		lblWelcomeThisIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeThisIs.setForeground(java.awt.Color.ORANGE);
		lblWelcomeThisIs.setFont(new Font("Arno Pro", Font.BOLD, 25));
		lblWelcomeThisIs.setBounds(274, 22, 309, 39);
		newacc.add(lblWelcomeThisIs);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(java.awt.Color.WHITE);
		lblName.setFont(new Font("Arial", Font.BOLD, 20));
		lblName.setBounds(80, 166, 127, 39);
		newacc.add(lblName);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setForeground(java.awt.Color.WHITE);
		lblPhone.setFont(new Font("Arial", Font.BOLD, 20));
		lblPhone.setBounds(421, 87, 127, 39);
		newacc.add(lblPhone);
		
		name = new JTextField();
		name.setFont(new Font("굴림", Font.BOLD, 18));
		name.setBounds(219, 164, 164, 43);
		newacc.add(name);
		name.setColumns(10);
		
		phone = new JTextField();
		phone.setFont(new Font("굴림", Font.BOLD, 18));
		phone.setColumns(10);
		phone.setBounds(549, 83, 164, 43);
		newacc.add(phone);
		
		JButton submit = new JButton("");
		submit.setIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Submit.jpg"));
		submit.setPressedIcon(new ImageIcon("G:\\Java_Work\\Lib_App\\Image\\Submit_clicked.jpg"));
		submit.setBackground(java.awt.Color.ORANGE);
		submit.setForeground(java.awt.Color.BLACK);
		submit.setFont(new Font("굴림", Font.BOLD, 18));
		submit.setBounds(538, 391, 170, 38);
		newacc.add(submit);
		
		idx = new JTextField();
		idx.setFont(new Font("굴림", Font.BOLD, 18));
		idx.setColumns(10);
		idx.setBounds(219, 87, 164, 43);
		newacc.add(idx);
		
		JLabel lblId_1 = new JLabel("Id");
		lblId_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblId_1.setForeground(java.awt.Color.WHITE);
		lblId_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblId_1.setBounds(87, 93, 127, 39);
		newacc.add(lblId_1);
		
		JLabel Password1 = new JLabel("Password");
		Password1.setHorizontalAlignment(SwingConstants.CENTER);
		Password1.setForeground(java.awt.Color.WHITE);
		Password1.setFont(new Font("Arial", Font.BOLD, 20));
		Password1.setBounds(80, 235, 127, 39);
		newacc.add(Password1);
		
		pass1 = new JPasswordField();
		pass1.setFont(new Font("굴림", Font.PLAIN, 18));
		pass1.setBounds(219, 233, 164, 43);
		newacc.add(pass1);
		
		pass2 = new JPasswordField();
		pass2.setFont(new Font("굴림", Font.PLAIN, 18));
		pass2.setBounds(219, 302, 164, 43);
		newacc.add(pass2);
		
		JLabel password2 = new JLabel("Password(again)");
		password2.setHorizontalAlignment(SwingConstants.CENTER);
		password2.setForeground(java.awt.Color.WHITE);
		password2.setFont(new Font("Arial", Font.BOLD, 20));
		password2.setBounds(49, 304, 160, 39);
		newacc.add(password2);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String idTxt = idx.getText();
				String nameTxt = name.getText();
				String phoneTxt = phone.getText();
				String passTxt = pass1.getText();
				String passTxt2 = pass2.getText();
					if(passTxt.equals(passTxt2))
					{
						customer.createCustomer(idTxt, passTxt, nameTxt, phoneTxt);
						JOptionPane.showMessageDialog(null,"Your data has been saved");
						welcomePanel.setVisible(true);
						newacc.setVisible(false);	
					}else{
						JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.");
					}
	
			}
		});

		newaccount.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
					welcomePanel.setVisible(false);
					newacc.setVisible(true);
			}
		});
		
		frame.setJMenuBar(menuBar());
		frame.setResizable(false); 
		frame.setLocationRelativeTo(null);
	}
	
	public JMenuBar menuBar(){
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu aboutMenu = new JMenu("About");
		
		bar.add(fileMenu);
		bar.add(aboutMenu);
		
		JMenuItem openFile = new JMenuItem("Open");
		JMenuItem exit = new JMenuItem("Exit");
		fileMenu.add(openFile);
		fileMenu.addSeparator();
		fileMenu.add(exit);
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		
		return bar;
	}
}

class ImagePanel extends JPanel{
	private Image img;
	
	public ImagePanel(Image img){
		this.img = img;
		setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setLayout(null);
	}
	
	public int getWidth(){
		return img.getWidth(null);
	}
	
	public int getHeight(){
		return img.getHeight(null);
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
}




