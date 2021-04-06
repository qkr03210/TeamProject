import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserAddPanel_cls extends JPanel {
	private JTextField txt_id;
	private JTextField txt_pwd;
	private JTextField txt_pwd2;
	private JTextField txt_name;
	private JTextField txt_phone;
	private boolean flag = false;
	
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
				if(ck.checkId()==0)
				{
					flag=true;
					JOptionPane.showMessageDialog(null, "사용하실수 있는 아이디입니다");
				}
				else
				{
					flag=false;
					JOptionPane.showMessageDialog(null, "사용중인 아이디입니다");
				}
					
				
			}
		});
		btn_idCheck.setBounds(534, 119, 97, 23);
		UserPanel.add(btn_idCheck);

		JLabel lb_PWD = new JLabel("PWD");
		lb_PWD.setBounds(271, 161, 57, 15);
		UserPanel.add(lb_PWD);

		txt_pwd = new JTextField();
		txt_pwd.setColumns(10);
		txt_pwd.setBounds(387, 155, 116, 21);
		UserPanel.add(txt_pwd);

		JLabel lb_rePWD = new JLabel("RE_PWD");
		lb_rePWD.setBounds(271, 196, 57, 15);
		UserPanel.add(lb_rePWD);

		txt_pwd2 = new JTextField();
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
				if(flag==false)
				{
					JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요");
				}
				else
				{
					if (txt_pwd.getText().equals((txt_pwd2).getText())) {

						UserAdd Uad= new UserAdd(txt_id,txt_pwd,txt_name,txt_phone);
						
						JOptionPane.showMessageDialog(null, "회원가입 성공");
						LoginPanel lp = new LoginPanel();
						MyProject.ChangePanel.removeAll();	
						MyProject.ChangePanel.add(lp);
						MyProject.ChangePanel.repaint();
						MyProject.ChangePanel.revalidate();
					}
					else
						JOptionPane.showMessageDialog(null, "비밀번호 재확인을 실패하셨습니다");
				}
				
			}
		});
		addUserBtn.setBounds(534, 316, 97, 23);
		UserPanel.add(addUserBtn);
	}
}
