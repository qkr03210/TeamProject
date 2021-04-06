import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserAddPanel_cls extends JPanel {
	private JTextField txt_id;
	private JPasswordField txt_pwd;
	private JPasswordField txt_pwd2;
	private JTextField txt_name;
	private JTextField txt_phone;
	private boolean flag_id_dup = false;

	Check ck;

	public UserAddPanel_cls() {
		setLayout(null);
		this.setSize(1000, 700);
		JPanel UserPanel = new JPanel();
		UserPanel.setLayout(null);
		UserPanel.setBounds(0, 0, 1000, 700);
		add(UserPanel);

		Check ck = new Check();

		JLabel lb_ID = new JLabel("ID");
		lb_ID.setBounds(271, 126, 57, 15);
		UserPanel.add(lb_ID);

		txt_id = new JTextField();
		txt_id.setColumns(10);
		txt_id.setBounds(387, 120, 116, 21);
		UserPanel.add(txt_id);

		JButton btn_idCheck = new JButton("ID CHECK");
		btn_idCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ck.setTxt(txt_id.getText());

				if (ck.checkId() == 0) {
					// 회원가입 id 널 값 처리 (김민성)
					if (txt_id.getText().equals("")) {
						flag_id_dup = false;
						JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
					} else {
						flag_id_dup = true;
						JOptionPane.showMessageDialog(null, "사용하실수 있는 아이디입니다");
					}
				} else {
					flag_id_dup = false;
					JOptionPane.showMessageDialog(null, "사용중인 아이디입니다");
				}

			}
		});
		btn_idCheck.setBounds(534, 119, 97, 23);
		UserPanel.add(btn_idCheck);

		JLabel lb_PWD = new JLabel("PWD");
		lb_PWD.setBounds(271, 161, 57, 15);
		UserPanel.add(lb_PWD);

		txt_pwd = new JPasswordField();
		txt_pwd.setColumns(10);
		txt_pwd.setBounds(387, 155, 116, 21);
		UserPanel.add(txt_pwd);

		JLabel lb_rePWD = new JLabel("RE_PWD");
		lb_rePWD.setBounds(271, 196, 57, 15);
		UserPanel.add(lb_rePWD);

		txt_pwd2 = new JPasswordField();
		txt_pwd2.setColumns(10);
		txt_pwd2.setBounds(387, 190, 116, 21);
		UserPanel.add(txt_pwd2);

		JLabel lb_NAME = new JLabel("NAME");
		lb_NAME.setBounds(271, 231, 57, 15);
		UserPanel.add(lb_NAME);

		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(387, 225, 116, 21);
		UserPanel.add(txt_name);

		JLabel lb_PHONENUMBER = new JLabel("PHONENUMBER");
		lb_PHONENUMBER.setBounds(271, 275, 104, 15);
		UserPanel.add(lb_PHONENUMBER);

		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(387, 269, 116, 21);
		UserPanel.add(txt_phone);

		JButton addUserBtn = new JButton("회원 가입");
		addUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/// edit by jaemoonnlee
				if (!flag_id_dup)
					JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요");
				else
					doCheckAll();

//				// 회원 가입시 널 값 처리 (김민성)
//				if (!txt_name.getText().equals("") && !txt_phone.getText().equals("")) {
//					if (flag_id_dup == false) {
//						JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요");
//					} else {
//						if (ck.get_Pass(txt_pwd).equals(ck.get_Pass(txt_pwd2))) {
//							// 비밀번호 확인 수정 (김민성)
//							UserAdd Uad = new UserAdd(txt_id, txt_pwd, txt_name, txt_phone);
//
//							JOptionPane.showMessageDialog(null, "회원가입 성공");
//							LoginPanel lp = new LoginPanel();
//							MyProject.ChangePanel.removeAll();
//							MyProject.ChangePanel.add(lp);
//							MyProject.ChangePanel.repaint();
//							MyProject.ChangePanel.revalidate();
//						} else {
//							JOptionPane.showMessageDialog(null, "비밀번호 재확인을 실패하셨습니다");
//						}
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "빈 정보가 없게 해주세요");
//				}
			}
		});
		addUserBtn.setBounds(534, 316, 97, 23);
		UserPanel.add(addUserBtn);
	}

	/// edit by jaemoonnlee
	private void doCheckAll() {
		// ID, 비밀번호, 전화번호 정규식 체크 및 null 체크
		// 비밀번호, 비밀번호 확인 서로 같은지 확인
		// variables
		String temp_pw = Helper.get_Pass(txt_pwd).trim();
		String temp_repw = Helper.get_Pass(txt_pwd2).trim();
		String temp_name = txt_name.getText().trim();
		boolean flag_id = false;
		boolean flag_pw = false;
		boolean flag_pw2 = false;
		boolean flag_name = false;
		boolean flag_phonenum = false;

		// ID
		flag_id = checkTextField(txt_id, INPUT_INFO.ID);
		// pw
		flag_pw = checkTextField(txt_pwd, INPUT_INFO.PW);
		// repw
		if (!temp_repw.equals("")) {
			if (temp_repw.equals(temp_pw)) {
				// 비번과 같은지 확인
				flag_pw2 = true;
			} else {
				// 정규식 불만족
				JOptionPane.showMessageDialog(null, "입력한 PW가 서로 같지 않습니다.\n다시 입력해주세요.", "비밀번호 확인",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "빈 칸일 수 없습니다.\n다시 입력해주세요.", "비밀번호 확인", JOptionPane.ERROR_MESSAGE);
		}

		// name
		if (!temp_name.equals("")) {
			// not null 만족
			flag_name = true;
		} else {
			JOptionPane.showMessageDialog(null, "빈 칸일 수 없습니다.\n다시 입력해주세요.", "이름", JOptionPane.ERROR_MESSAGE);
		}
		// phoneNumber
		flag_phonenum = checkTextField(txt_phone, INPUT_INFO.PHONENUM);

		if (flag_id && flag_pw && flag_pw2 && flag_name && flag_phonenum) {
			// run query if all condition is true
			if (Helper.get_Pass(txt_pwd).equals(Helper.get_Pass(txt_pwd2))) {
				UserAdd Uad = new UserAdd(txt_id, txt_pwd, txt_name, txt_phone);

				JOptionPane.showMessageDialog(null, "회원가입 성공");
				LoginPanel lp = new LoginPanel();
				MyProject.ChangePanel.removeAll();
				MyProject.ChangePanel.add(lp);
				MyProject.ChangePanel.repaint();
				MyProject.ChangePanel.revalidate();
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호 재확인을 실패하셨습니다");
			}
			return;
		}

		// field reset(setFocus 때문에 아래쪽부터 확인)
		if (!flag_phonenum) {
			txt_phone.setText("");
			txt_phone.requestFocus();
		}
		if (!flag_name) {
			txt_name.setText("");
			txt_name.requestFocus();
		}
		if (!flag_pw2) {
			txt_pwd2.setText("");
			txt_pwd2.requestFocus();
		}
		if (!flag_pw) {
			txt_pwd.setText("");
			txt_pwd.requestFocus();
		}
		if (!flag_id) {
			txt_id.setText("");
			txt_id.requestFocus();
		}
	}

	/// edit by jaemoonnlee
	private boolean checkTextField(Object input, INPUT_INFO info) {
		boolean flag = false;
		String temp_str;
		// check input type & remove blank character from both side.
		if (info == INPUT_INFO.PW)
			temp_str = Helper.get_Pass((JPasswordField) input).trim();
		else
			temp_str = ((JTextField) input).getText().trim();

		// 빈칸 확인
		if (!temp_str.equals("")) {
			// 정규식 확인
			if (info == INPUT_INFO.ID) {
				if (Helper.isID(temp_str)) {
					flag = true;
				} else {
					JOptionPane.showMessageDialog(null, "길이 5~12의 영문, 숫자, (_)로만 이루어집니다.\n다시 입력해주세요.", "ID",
							JOptionPane.ERROR_MESSAGE);
				}
			} else if (info == INPUT_INFO.PHONENUM) {
				if (Helper.isPhoneNumber(temp_str)) {
					flag = true;
				} else {
					JOptionPane.showMessageDialog(null,
							"[x = 0, 1, 6, 7, 8, 9]\n01x - 3~4자리숫자 - 4자리숫자 OR\n01x . 3~4자리숫자 . 4자리숫자 OR\n 01x 3~4자리숫자 4자리숫자로 이루어집니다.\n다시 입력해주세요.",
							"휴대전화번호", JOptionPane.ERROR_MESSAGE);
				}
			} else if (info == INPUT_INFO.PW) {
				if (Helper.isPassword(temp_str)) {
					flag = true;
				} else {
					JOptionPane.showMessageDialog(null, "<필수>\n길이 최소 8자\n영문(대소문자 최소 1개 필수)\n숫자\n공백 불가\n다시 입력해주세요.",
							"비밀번호", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			switch (info) {
			case ID:
				JOptionPane.showMessageDialog(null, "입력란은 빈 칸일 수 없습니다.\n다시 입력해주세요.", "ID", JOptionPane.ERROR_MESSAGE);
				break;
			case PW:
				JOptionPane.showMessageDialog(null, "입력란은 빈 칸일 수 없습니다.\n다시 입력해주세요.", "비밀번호", JOptionPane.ERROR_MESSAGE);
				break;
			case PHONENUM:
				JOptionPane.showMessageDialog(null, "입력란은 빈 칸일 수 없습니다.\n다시 입력해주세요.", "휴대전화번호",
						JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
		return flag;
	}

	private enum INPUT_INFO {
		ID, PW, PHONENUM
	}
}
