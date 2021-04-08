package swing;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.FindUid;
import java.awt.event.ActionEvent;

public class FindUidPanel extends JPanel{
	private JTextField textField;
	private JTextField textField_1;
	public FindUidPanel(){
		setSize(1000,700);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디 찾기");
		lblNewLabel.setBounds(405, 174, 167, 21);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(297, 229, 82, 21);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(406, 226, 166, 27);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("전화번호");
		lblNewLabel_2.setBounds(297, 312, 82, 21);
		add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(405, 309, 166, 27);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("아이디 찾기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindUid fu=new FindUid(textField,textField_1);
			}
		});
		btnNewButton.setBounds(429, 396, 129, 29);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("돌아가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanel lp = new LoginPanel();
				MyProject.switchTopPanel(lp);
//				MyProject.ChangePanel.removeAll();
//				MyProject.ChangePanel.add(lp);
//				MyProject.ChangePanel.repaint();
//				MyProject.ChangePanel.revalidate();
			}
		});
		btnNewButton_1.setBounds(781, 614, 129, 29);
		add(btnNewButton_1);
	}
}
