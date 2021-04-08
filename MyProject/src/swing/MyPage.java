package swing;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class MyPage extends JPanel{
	public static JPanel panel;
	MyInfo mf = new MyInfo();
	public MyPage(){
		setLayout(null);
		this.setSize(1000, 700);
		
		JButton update_btn = new JButton("수정");
		
		update_btn.setBounds(75, 52, 97, 23);
		add(update_btn);
		
		// 조회 버튼 구현 (김민성)
		JButton view_btn = new JButton("조회");
		view_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cards =  (CardLayout) panel.getLayout();
				cards.show(panel, "info");
			}
		});
		view_btn.setBounds(75, 108, 97, 23);
		add(view_btn);
		
		JButton return_btn = new JButton("돌아가기");
		return_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyProject.switchTopPanel(MyProject.smp);
//				MyProject.ChangePanel.removeAll();
//				MyProject.ChangePanel.add(MyProject.smp);
//				MyProject.ChangePanel.repaint();
//				MyProject.ChangePanel.revalidate();
			}
		});
		return_btn.setBounds(75, 253, 97, 23);
		add(return_btn);
		
		JButton delete_btn = new JButton("탈퇴");
		delete_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cards =  (CardLayout) panel.getLayout();            
	            cards.show(panel, "up3");
			}
		});
		delete_btn.setBounds(75, 169, 97, 23);
		add(delete_btn);
		
		panel= new JPanel();
		panel.setBounds(185, 25, 790, 648);
		add(panel);
		panel.setLayout(new CardLayout(0, 0));
		MyInfo info = new MyInfo();
		panel.add(info,"info");
		Reconfirm up1 =new Reconfirm(1);
		panel.add(up1,"up1");
		Reconfirm up3 = new Reconfirm(2);
		panel.add(up3,"up3");
		UpdatePanel2 up2 = new UpdatePanel2();
		panel.add(up2,"up2");
		MyInfo mi = new MyInfo();
		panel.add(mi,"mi");
		update_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cards =  (CardLayout) panel.getLayout();            
	            cards.show(panel, "up1");
			}
		});
	}
}
