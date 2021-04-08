package swing;

import java.Check;
import java.DeleteUser;
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

	// 비밀번호전용 필드로 변경 (김민성)

	private JPasswordField passwordField;

	public Reconfirm(int version) {
		setSize(790, 648);
		setLayout(null);

		Panel panel = new Panel();
		panel.setBounds(135, 174, 520, 324);
		add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Check ck = new Check();
				if (ck.checkPwd(MyProject.UserId, ck.get_Pass(passwordField)) && version == 1) {
					CardLayout cards = (CardLayout) MyPage.panel.getLayout();
					cards.show(MyPage.panel, "up2");
				} else if (ck.checkPwd(MyProject.UserId, ck.get_Pass(passwordField)) && version == 2) {
					DeleteUser du = new DeleteUser();
					LoginPanel lp = new LoginPanel();
					MyProject.switchTopPanel(lp);
//					MyProject.ChangePanel.removeAll();
//					MyProject.ChangePanel.add(lp);
//					MyProject.ChangePanel.repaint();
//					MyProject.ChangePanel.revalidate();
				} else
					JOptionPane.showMessageDialog(null, "비밀번호를 잘못 입력하셨습니다");
			}
		});
//		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(201, 158, 75, 23);
		panel.add(btnNewButton);

		JLabel label = new JLabel("비밀번호를 한번 더 입력해주세요.");
		label.setBounds(42, 43, 328, 15);
		panel.add(label);

		JLabel lblNewLabel = new JLabel("회원님의 정보를 안전하게 보호하기 위해 비밀번호를 한번 더 확인합니다");
		lblNewLabel.setBounds(42, 68, 413, 15);
		panel.add(lblNewLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(186, 106, 104, 23);
		panel.add(passwordField);

		JLabel lb_ckPwd = new JLabel("회원 비밀번호 확인");
		lb_ckPwd.setForeground(Color.RED);
		lb_ckPwd.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lb_ckPwd.setBounds(253, 112, 249, 56);
		add(lb_ckPwd);
	}

}
