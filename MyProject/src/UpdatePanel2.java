import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdatePanel2 extends JPanel{
	public static JTextField textField_1;
	 
	public UpdatePanel2()
	{
		setSize(790, 648);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(208, 205, 57, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("변경할 password");
		lblNewLabel_1.setBounds(208, 259, 57, 15);
		add(lblNewLabel_1);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(484, 256, 116, 21);
		add(textField_1);
		textField_1.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("수정");
		Check ck = new Check();
		// 비밀번호 변경 (김민성)
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().equals(ck.get_Pass(LoginPanel.passwordField))) {
					JOptionPane.showMessageDialog(null, "사용하고 있는 비밀번호입니다 ");
					return;
				}
				
				else{
					new Modified_Pass();
					JOptionPane.showMessageDialog(null, "수정 되었습니다");
				}
			}
		});
		btnNewButton.setBounds(503, 443, 97, 23);
		add(btnNewButton);
		
		
		
		
		
		JLabel lblNewLabel_4 = new JLabel(MyProject.UserId);
		lblNewLabel_4.setBounds(503, 205, 57, 15);
		add(lblNewLabel_4);
	}
	
	
}
