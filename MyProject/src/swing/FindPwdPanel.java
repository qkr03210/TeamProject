package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindPwdPanel extends JPanel {
	private JLabel lbl_name;
	private JLabel lbl_id;
	private JLabel lbl_phone;

	private JTextField txt_name;
	private JTextField txt_id;
	private JTextField txt_phone;

	private JButton btn_find_pw;
	private JButton btnNewButton;

	public FindPwdPanel() {
		setLayout(null);
		this.setSize(1000, 700);

		lbl_name = new JLabel("이름");
		lbl_name.setBounds(312, 262, 57, 15);
		add(lbl_name);

		txt_name = new JTextField();
		txt_name.setBounds(401, 259, 116, 21);
		add(txt_name);
		txt_name.setColumns(10);

		lbl_id = new JLabel("ID");
		lbl_id.setBounds(312, 220, 57, 15);
		add(lbl_id);

		txt_id = new JTextField();
		txt_id.setBounds(401, 217, 116, 21);
		add(txt_id);
		txt_id.setColumns(10);

		btn_find_pw = new JButton("비밀번호 찾기");

		btn_find_pw.setBounds(357, 383, 160, 23);
		add(btn_find_pw);

		lbl_phone = new JLabel("연락처");
		lbl_phone.setBounds(312, 315, 57, 15);
		add(lbl_phone);

		txt_phone = new JTextField();
		txt_phone.setBounds(401, 312, 116, 21);
		add(txt_phone);
		txt_phone.setColumns(10);

		btnNewButton = new JButton("돌아가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanel lp = new LoginPanel();
				MyProject.switchTopPanel(lp);
			}
		});
		btnNewButton.setBounds(803, 622, 129, 29);
		add(btnNewButton);

		btn_find_pw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyProject.dml.findPw(txt_id, txt_name, txt_phone);
			}
		});
	}
}
