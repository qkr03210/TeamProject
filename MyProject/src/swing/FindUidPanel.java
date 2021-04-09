package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindUidPanel extends JPanel {
	private JTextField txt_name;
	private JTextField txt_phone;
	private JLabel lbl_title_find_id;
	private JLabel lbl_name;
	private JLabel lbl_phone;
	private JButton btn_find_id;
	private JButton btn_goback;

	public FindUidPanel() {
		setSize(1000, 700);
		setLayout(null);

		lbl_title_find_id = new JLabel("아이디 찾기");
		lbl_title_find_id.setBounds(405, 174, 167, 21);
		add(lbl_title_find_id);

		lbl_name = new JLabel("이름");
		lbl_name.setBounds(297, 229, 82, 21);
		add(lbl_name);

		txt_name = new JTextField();
		txt_name.setBounds(406, 226, 166, 27);
		add(txt_name);
		txt_name.setColumns(10);

		lbl_phone = new JLabel("전화번호");
		lbl_phone.setBounds(297, 312, 82, 21);
		add(lbl_phone);

		txt_phone = new JTextField();
		txt_phone.setBounds(405, 309, 166, 27);
		add(txt_phone);
		txt_phone.setColumns(10);

		btn_find_id = new JButton("아이디 찾기");
		btn_find_id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MyProject.dml.findId(txt_name, txt_phone);
			}
		});
		btn_find_id.setBounds(429, 396, 129, 29);
		add(btn_find_id);

		btn_goback = new JButton("돌아가기");
		btn_goback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanel lp = new LoginPanel();
				MyProject.switchTopPanel(lp);
			}
		});
		btn_goback.setBounds(781, 614, 129, 29);
		add(btn_goback);
	}
}
