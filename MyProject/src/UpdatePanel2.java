import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UpdatePanel2 extends JPanel{
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public UpdatePanel2()
	{
		setSize(790, 648);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(208, 205, 57, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(208, 259, 57, 15);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(208, 312, 57, 15);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(208, 368, 57, 15);
		add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(484, 256, 116, 21);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(484, 309, 116, 21);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(484, 365, 116, 21);
		add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("수정");
		btnNewButton.setBounds(503, 443, 97, 23);
		add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel(MyProject.UserId);
		lblNewLabel_4.setBounds(503, 205, 57, 15);
		add(lblNewLabel_4);
	}
}
