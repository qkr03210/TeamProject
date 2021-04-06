import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class LoginPanel extends JPanel {
	private JPasswordField passwordField;
	Check ck = new Check();
	public LoginPanel(){
		setSize(1000,700);
		setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(323, 258, 345, 21);
		add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("로그인");
		label.setBounds(456, 222, 45, 21);
		add(label);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

		JLabel lblNewLabel = new JLabel("회원 가입");
		lblNewLabel.setBounds(296, 319, 76, 15);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("비밀번호 찾기");

		lblNewLabel_1.setBounds(607, 319, 102, 15);
		add(lblNewLabel_1);

		JButton btnNewButton = new JButton("로그인");

		btnNewButton.setBounds(383, 360, 190, 30);
		add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("아이디 찾기");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FindUidPanel fup = new FindUidPanel();
				MyProject.ChangePanel.removeAll();
				MyProject.ChangePanel.add(fup);
				MyProject.ChangePanel.repaint();
				MyProject.ChangePanel.revalidate();
			}
		});
		lblNewLabel_2.setBounds(456, 319, 82, 21);
		add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(323, 288, 345, 21);
		add(passwordField);
		
		//회원가입
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				UserAddPanel_cls uap_cls = new UserAddPanel_cls();
				MyProject.ChangePanel.removeAll();
				MyProject.ChangePanel.add(uap_cls);
				MyProject.ChangePanel.repaint();
				MyProject.ChangePanel.revalidate();
			}
		});
		//로그인
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// if()
				// else
				Login_cls login_cls = new Login_cls(textField.getText(),ck.get_Pass(passwordField));
				 
				if(login_cls.checkLogin()==2)
				{
					SelectMenuPanel smp = new SelectMenuPanel();
					MyProject.UserId=textField.getText();
					MyProject.ChangePanel.removeAll();
					MyProject.ChangePanel.add(smp);
					MyProject.ChangePanel.repaint();
					MyProject.ChangePanel.revalidate();
				}
			}
		});
		//비밀번호 찾기
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FindPwdPanel fpp = new FindPwdPanel();
				MyProject.ChangePanel.removeAll();
				MyProject.ChangePanel.add(fpp);
				MyProject.ChangePanel.repaint();
				MyProject.ChangePanel.revalidate();
			}
		});
	}
}
