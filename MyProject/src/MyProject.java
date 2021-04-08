import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MyProject extends JFrame {
	// static variables
	public static String UserId = "";
	public static JLayeredPane ChangePanel;
	public static JPanel LoginPanel;
	public static SelectMenuPanel smp = new SelectMenuPanel();

	// Component
	private JPanel MainPanel;
	private JPanel Book_MS_Panel;
	private JPanel Reserv_Panel;
	private JButton btnNewButton_1;
	private JButton btn_Reserv;
	private JButton returnLogin;
	private JButton btn_Reserv_ms;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	JLabel[] selectedTable = new JLabel[5];

	public MyProject() {
		initaialize();
		setEvent();
	}

	private void initaialize() {

		getContentPane().setLayout(null);

		ChangePanel = new JLayeredPane();
		ChangePanel.setBounds(0, 0, 1000, 700);
		getContentPane().add(ChangePanel);

		LoginPanel lp = new LoginPanel();
		ChangePanel.add(lp);

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
				ChangePanel.removeAll();
				ChangePanel.add(smp);
				ChangePanel.repaint();
				ChangePanel.revalidate();

			}
		});
	}

	public static void main(String[] args) {
		new MyProject();
	}

}
