package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java_side.Helper;

public class UpdatePanel2 extends JPanel {
	private JTextField txt_pw;
	private JLabel lbl_id, lbl_id_show, lbl_pw;
	private JButton btn_update;

	public UpdatePanel2() {
		setSize(790, 648);
		setLayout(null);

		lbl_id = new JLabel("ID");
		lbl_id.setBounds(208, 205, 57, 15);
		add(lbl_id);

		lbl_pw = new JLabel("변경할 password");
		lbl_pw.setBounds(208, 259, 105, 15);
		add(lbl_pw);

		txt_pw = new JTextField();
		txt_pw.setBounds(484, 256, 116, 21);
		add(txt_pw);
		txt_pw.setColumns(10);

		btn_update = new JButton("수정");
		// 비밀번호 변경 (김민성)
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_pw.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
				} else if (txt_pw.getText().equals(Helper.get_Pass(LoginPanel.passwordField))) {
					JOptionPane.showMessageDialog(null, "사용하고 있는 비밀번호입니다 ");
					return;
				} else {
					MyProject.dml.changePw(txt_pw);
					JOptionPane.showMessageDialog(null, "수정 되었습니다");
				}
			}
		});
		btn_update.setBounds(503, 443, 97, 23);
		add(btn_update);

		lbl_id_show = new JLabel(MyProject.UserId);
		lbl_id_show.setBounds(503, 205, 57, 15);
		add(lbl_id_show);
	}

}
