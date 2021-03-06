package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java_side.Helper;

public class UserAddPanel_cls extends JPanel {
	private JPanel pnl_user;
	private JPasswordField txt_pw, txt_repw;
	private JTextField txt_id, txt_name, txt_phone;
	private JLabel lbl_id, lbl_pw, lbl_repw, lbl_name, lbl_phone;
	private JButton btn_addUser;

	private boolean flag_id_dup = false;

	public UserAddPanel_cls() {
		setLayout(null);
		this.setSize(1000, 700);
		pnl_user = new JPanel();
		pnl_user.setLayout(null);
		pnl_user.setBounds(0, 0, 1000, 700);
		add(pnl_user);


		lbl_id = new JLabel("ID");
		lbl_id.setBounds(271, 126, 57, 15);
		pnl_user.add(lbl_id);

		txt_id = new JTextField();
		txt_id.setColumns(10);
		txt_id.setBounds(387, 120, 116, 21);
		pnl_user.add(txt_id);

		JButton btn_idCheck = new JButton("ID CHECK");
		btn_idCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = MyProject.dml.checkIdDup(txt_id.getText());
				if (result == 0) {
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
		pnl_user.add(btn_idCheck);

		lbl_pw = new JLabel("PWD");
		lbl_pw.setBounds(271, 161, 57, 15);
		pnl_user.add(lbl_pw);

		txt_pw = new JPasswordField();
		txt_pw.setColumns(10);
		txt_pw.setBounds(387, 155, 116, 21);
		pnl_user.add(txt_pw);

		lbl_repw = new JLabel("RE_PWD");
		lbl_repw.setBounds(271, 196, 57, 15);
		pnl_user.add(lbl_repw);

		txt_repw = new JPasswordField();
		txt_repw.setColumns(10);
		txt_repw.setBounds(387, 190, 116, 21);
		pnl_user.add(txt_repw);

		lbl_name = new JLabel("NAME");
		lbl_name.setBounds(271, 231, 57, 15);
		pnl_user.add(lbl_name);

		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(387, 225, 116, 21);
		pnl_user.add(txt_name);

		lbl_phone = new JLabel("PHONENUMBER");
		lbl_phone.setBounds(271, 275, 104, 15);
		pnl_user.add(lbl_phone);

		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(387, 269, 116, 21);
		pnl_user.add(txt_phone);

		btn_addUser = new JButton("회원 가입");
		btn_addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btn_addUser.setBounds(534, 316, 97, 23);
		pnl_user.add(btn_addUser);
	}

	/**
	 * ID, PW, PW확인, 이름, 전화번호 입력값을 확인 후 DB에 입력
	 * 
	 * @author jaemoonnlee
	 */
	private void doCheckAll() {
		// ID, 비밀번호, 전화번호 정규식 체크 및 null 체크
		// 비밀번호, 비밀번호 확인 서로 같은지 확인
		// variables
		String temp_pw = Helper.get_Pass(txt_pw).trim();
		String temp_repw = Helper.get_Pass(txt_repw).trim();
		String temp_name = txt_name.getText().trim();
		boolean flag_id = false;
		boolean flag_pw = false;
		boolean flag_pw2 = false;
		boolean flag_name = false;
		boolean flag_phonenum = false;

		// ID
		flag_id = checkTextField(txt_id, INPUT_INFO.ID);
		// pw
		flag_pw = checkTextField(txt_pw, INPUT_INFO.PW);
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
			MyProject.dml.addUser(txt_id, txt_pw, txt_name, txt_phone);

			JOptionPane.showMessageDialog(null, "회원가입 성공");
			LoginPanel lp = new LoginPanel();
			MyProject.switchTopPanel(lp);
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
			txt_repw.setText("");
			txt_repw.requestFocus();
		}
		if (!flag_pw) {
			txt_pw.setText("");
			txt_pw.requestFocus();
		}
		if (!flag_id) {
			txt_id.setText("");
			txt_id.requestFocus();
		}
	}

	/**
	 * 입력값이 빈칸인지 확인하고, 입력값이 정규식에 맞는지 아닌지를 확인하는 메소드
	 * 
	 * @param input JTextField | JPasswordField
	 * @param info  INPUT_INFO: ID, PW, PHONENUM
	 * @return
	 * @author jaemoonnlee
	 */
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

	/**
	 * @author jaemoonnlee
	 */
	private enum INPUT_INFO {
		ID, PW, PHONENUM
	}
}
