package swing;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MyPage extends JPanel {
	public static JPanel pnl_sub;
	MyInfo mf = new MyInfo();
	private JButton btn_update;
	private JButton btn_select;
	JButton btn_goback;
	JButton btn_delete;
	MyInfo myinfo01;
//	MyInfo myinfo02;
	Reconfirm checkpw01;
	Reconfirm checkpw03;
	UpdatePanel2 updatePnl02;

	public MyPage() {
		setLayout(null);
		this.setSize(1000, 700);

		btn_update = new JButton("수정");
		btn_update.setBounds(75, 52, 97, 23);
		add(btn_update);

		// 조회 버튼 구현 (김민성)
		btn_select = new JButton("조회");
		btn_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cards = (CardLayout) pnl_sub.getLayout();
				cards.show(pnl_sub, "info");
			}
		});
		btn_select.setBounds(75, 108, 97, 23);
		add(btn_select);

		btn_goback = new JButton("돌아가기");
		btn_goback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyProject.switchTopPanel(MyProject.smp);
			}
		});
		btn_goback.setBounds(75, 253, 97, 23);
		add(btn_goback);

		btn_delete = new JButton("탈퇴");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cards = (CardLayout) pnl_sub.getLayout();
				cards.show(pnl_sub, "up3");
			}
		});
		btn_delete.setBounds(75, 169, 97, 23);
		add(btn_delete);

		pnl_sub = new JPanel();
		pnl_sub.setBounds(185, 25, 790, 648);
		add(pnl_sub);
		pnl_sub.setLayout(new CardLayout(0, 0));

		// 조회
		myinfo01 = new MyInfo();
		pnl_sub.add(myinfo01, "info");

		// 유저 정보 수정
		checkpw01 = new Reconfirm(1);
		pnl_sub.add(checkpw01, "up1");

		// 유저 정보 삭제
		checkpw03 = new Reconfirm(2);
		pnl_sub.add(checkpw03, "up3");
		
		// 비밀번호 변경?
		updatePnl02 = new UpdatePanel2();
		pnl_sub.add(updatePnl02, "up2");

		// 안쓰이는데?
//		myinfo02 = new MyInfo();
//		pnl_sub.add(myinfo02, "mi");

		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cards = (CardLayout) pnl_sub.getLayout();
				cards.show(pnl_sub, "up1");
			}
		});
	}
}
