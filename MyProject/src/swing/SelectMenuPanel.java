package swing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SelectMenuPanel extends JPanel{
	public SelectMenuPanel() {
		setSize(1000,700);
		setLayout(null);
		
		JButton btn_UserInfo = new JButton("마이페이지");
		
		btn_UserInfo.setBounds(372, 264, 243, 72);
		add(btn_UserInfo);
		
		JButton btn_BookManager = new JButton("도서 검색");

		btn_BookManager.setBounds(372, 346, 243, 72);
		add(btn_BookManager);
		
		btn_BookManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book_MS_Panel bmp = new Book_MS_Panel();
				MyProject.switchTopPanel(bmp);
//				MyProject.ChangePanel.removeAll();
//				MyProject.ChangePanel.add(bmp);
//				MyProject.ChangePanel.repaint();
//				MyProject.ChangePanel.revalidate();
			}
		});
		btn_UserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MyPage mp = new MyPage();
				MyProject.switchTopPanel(mp);
//				MyProject.ChangePanel.removeAll();
//				MyProject.ChangePanel.add(mp);
//				MyProject.ChangePanel.repaint();
//				MyProject.ChangePanel.revalidate();
			}
		});
	}
}
