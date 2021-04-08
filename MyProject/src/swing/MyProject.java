package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.DBConnManager;

public class MyProject extends JFrame {
	// static variables
	public static String UserId = "";// ?
	/**
	 * 최상위 레이아웃 패널
	 */
	public static JLayeredPane ChangePanel;
	public static JPanel LoginPanel;
	public static SelectMenuPanel smp = new SelectMenuPanel();
	public static Connection mainConn = null;

	// Component
	private JPanel MainPanel;
	private JPanel Book_MS_Panel;
	private JPanel Reserv_Panel;
	private JButton btnNewButton_1;
	private JButton btn_Reserv;
	private JButton returnLogin;
	private JButton btn_Reserv_ms;

	JLabel[] selectedTable = new JLabel[5];

	public MyProject() {
		try {
			initaialize();
			setEvent();
//			mainConn = DBConnManager.getConn("ai", "1234");
//			if (mainConn != null) {
//				initaialize();
//				setEvent();
//			} else
//				JOptionPane.showMessageDialog(null, "no DB connection", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (mainConn != null)
				try {
					DBConnManager.disconn();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
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
				switchTopPanel(Reserv_Panel);
			}
		});
		returnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchTopPanel(LoginPanel);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchTopPanel(Book_MS_Panel);
			}
		});

		btn_Reserv_ms.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchTopPanel(smp);
			}
		});
	}

	// FIXME: 시작!
	public static void main(String[] args) {
		new MyProject();
	}

	/**
	 * 현재 최상단 레이아웃의 JPanel을 제거하고, 매개변수로 대체.
	 * 
	 * @param pan
	 * @author jaemoonnlee
	 */
	public static void switchTopPanel(JPanel pan) {
		ChangePanel.removeAll();
		ChangePanel.add(pan);
		ChangePanel.repaint();
		ChangePanel.revalidate();
	}
}
