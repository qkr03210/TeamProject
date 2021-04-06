import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindPwdPanel extends JPanel{
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	
	private JButton btnNewButton_4;
	private JButton btnNewButton;
	
	public FindPwdPanel(){
		setLayout(null);
		this.setSize(1000, 700);
		
		lblNewLabel_3 = new JLabel("이름");
		lblNewLabel_3.setBounds(312, 262, 57, 15);
		add(lblNewLabel_3);

		textField_7 = new JTextField();
		textField_7.setBounds(401, 259, 116, 21);
		add(textField_7);
		textField_7.setColumns(10);

		lblNewLabel_4 = new JLabel("ID");
		lblNewLabel_4.setBounds(312, 220, 57, 15);
		add(lblNewLabel_4);

		textField_8 = new JTextField();
		textField_8.setBounds(401, 217, 116, 21);
		add(textField_8);
		textField_8.setColumns(10);

		btnNewButton_4 = new JButton("비밀번호 찾기");

		btnNewButton_4.setBounds(357, 383, 160, 23);
		add(btnNewButton_4);

		lblNewLabel_5 = new JLabel("연락처");
		lblNewLabel_5.setBounds(312, 315, 57, 15);
		add(lblNewLabel_5);

		textField_9 = new JTextField();
		textField_9.setBounds(401, 312, 116, 21);
		add(textField_9);
		textField_9.setColumns(10);
		
		btnNewButton = new JButton("돌아가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanel lp = new LoginPanel();
				MyProject.ChangePanel.removeAll();
				MyProject.ChangePanel.add(lp);
				MyProject.ChangePanel.repaint();
				MyProject.ChangePanel.revalidate();
			}
		});
		btnNewButton.setBounds(803, 622, 129, 29);
		add(btnNewButton);
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindPwd fp=new FindPwd(textField_8,textField_7,textField_9);
			}
		});
	}
}
