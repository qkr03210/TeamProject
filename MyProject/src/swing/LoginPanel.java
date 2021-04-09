package swing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	// static variable -> UpdatePanel2.java
	public static JPasswordField passwordField;
	// variables(Components)
	private JTextField txt_id;
	private JLabel lbl_title_login;
	private JLabel lbl_register;
	private JLabel lbl_find_pw;
	private JButton btn_login;
	private JLabel lbl_find_id;

	public LoginPanel() {
		setSize(1000, 700);
		setLayout(null);

		txt_id = new JTextField();
		txt_id.setBounds(323, 258, 345, 21);
		add(txt_id);
		txt_id.setColumns(10);

		lbl_title_login = new JLabel("로그인");
		lbl_title_login.setBounds(456, 222, 45, 21);
		add(lbl_title_login);
		lbl_title_login.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

		lbl_register = new JLabel("회원 가입");
		lbl_register.setBounds(296, 319, 76, 15);
		add(lbl_register);

		lbl_find_pw = new JLabel("비밀번호 찾기");

		lbl_find_pw.setBounds(607, 319, 102, 15);
		add(lbl_find_pw);

		btn_login = new JButton("로그인");

		btn_login.setBounds(383, 360, 190, 30);
		add(btn_login);

		lbl_find_id = new JLabel("아이디 찾기");
		lbl_find_id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FindUidPanel fup = new FindUidPanel();
				MyProject.switchTopPanel(fup);
			}
		});
		lbl_find_id.setBounds(456, 319, 82, 21);
		add(lbl_find_id);

		passwordField = new JPasswordField();
		passwordField.setBounds(323, 288, 345, 21);
		add(passwordField);

		// 회원가입
		lbl_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				UserAddPanel_cls uap_cls = new UserAddPanel_cls();
				MyProject.switchTopPanel(uap_cls);
			}
		});
		// 로그인
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = MyProject.dml.login(txt_id.getText(),passwordField);
				if (result == 2) {
					SelectMenuPanel smp = new SelectMenuPanel();
					MyProject.UserId = txt_id.getText();
					MyProject.switchTopPanel(smp);
				}
			}
		});
		// 비밀번호 찾기
		lbl_find_pw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FindPwdPanel fpp = new FindPwdPanel();
				MyProject.switchTopPanel(fpp);
			}
		});
	}
}
