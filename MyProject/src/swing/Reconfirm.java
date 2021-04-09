package swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Reconfirm extends JPanel {
	private Panel pnl_this;
	private JButton btn_confirm;
	private JLabel lbl_content01;
	private JLabel lbl_content02;
	private JLabel lbl_title_recheck;
	// 비밀번호전용 필드로 변경 (김민성)
	private JPasswordField txtpw_pw;

	public Reconfirm(int version) {
		setSize(790, 648);
		setLayout(null);

		pnl_this = new Panel();
		pnl_this.setBounds(135, 174, 520, 324);
		add(pnl_this);
		pnl_this.setLayout(null);

		btn_confirm = new JButton("확인");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = MyProject.dml.checkPw(MyProject.UserId, txtpw_pw);
				if (result && version == 1) {
					CardLayout cards = (CardLayout) MyPage.pnl_sub.getLayout();
					cards.show(MyPage.pnl_sub, "up2");
				} else if (result && version == 2) {
					MyProject.dml.deleteUser();
					LoginPanel lp = new LoginPanel();
					MyProject.switchTopPanel(lp);
				} else
					JOptionPane.showMessageDialog(null, "비밀번호를 잘못 입력하셨습니다");
			}
		});
		btn_confirm.setBackground(Color.RED);
		btn_confirm.setBounds(201, 158, 75, 23);
		pnl_this.add(btn_confirm);

		lbl_content01 = new JLabel("비밀번호를 한번 더 입력해주세요.");
		lbl_content01.setBounds(42, 43, 328, 15);
		pnl_this.add(lbl_content01);

		lbl_content02 = new JLabel("회원님의 정보를 안전하게 보호하기 위해 비밀번호를 한번 더 확인합니다");
		lbl_content02.setBounds(42, 68, 413, 15);
		pnl_this.add(lbl_content02);

		txtpw_pw = new JPasswordField();
		txtpw_pw.setBounds(186, 106, 104, 23);
		pnl_this.add(txtpw_pw);

		lbl_title_recheck = new JLabel("회원 비밀번호 확인");
		lbl_title_recheck.setForeground(Color.RED);
		lbl_title_recheck.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lbl_title_recheck.setBounds(253, 112, 249, 56);
		add(lbl_title_recheck);
	}

}
