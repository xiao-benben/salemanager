package org.lqz.module.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.lqz.framework.util.Item;
import org.lqz.framework.util.MyFont;
import org.lqz.module.entity.User;
import org.lqz.module.services.Impl.UserServiceImpl;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class DeleteUserJFrame extends JFrame implements MouseListener {
	/*JFrame jFrame;
	JPanel jPanel;
	JLabel label_user;
	JComboBox userBox;
	JButton button_delete;
	
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public DeleteUserJFrame() {
		jFrame = new JFrame("删除用户");
		jPanel = new JPanel();
		userBox = new JComboBox<>();
		label_user = new JLabel("删除用户信息", JLabel.CENTER);
		label_user.setFont(MyFont.Static);
		jPanel.add(label_user);
		jPanel.add(userBox);
		button_delete = new JButton("删除");
		button_delete.addMouseListener(this);
		button_delete.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_delete.setForeground(Color.white);
		button_delete.setFont(MyFont.Static);
		jFrame.add(label_user, BorderLayout.NORTH);
		jFrame.add(jPanel, BorderLayout.CENTER);
		jFrame.add(button_delete, BorderLayout.SOUTH);
		
		jFrame.setSize(400, 250);
		jFrame.setVisible(true);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == button_delete) {
			if("请选择".equals(userBox.getSelectedItem())) {
				JOptionPane.showMessageDialog(null, "用户名不能为空");
			}
			else {
				JOptionPane.showMessageDialog(null, "");
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}*/
	JPanel backgroundPanel, namePanel, contentPanel, buttonPanel;
	JFrame jFrame;
	JLabel label_user;
	JComboBox<String> userBox;
	JButton button_delete;
	User user;
	List<Object[]> list_user;
	
	UserManagerJPanel parentPanel;
	// 获得屏幕的大小
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public DeleteUserJFrame(User user, UserManagerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		initBackgroundPanel();

		this.add(backgroundPanel);

		this.setTitle("删除用户");
		this.setSize(300, 200);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	// 初始化背景面板
	public void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());

		initContentPanel();
		initButtonPanel();
		initLabelPanel();

		backgroundPanel.add(namePanel, "North");
		backgroundPanel.add(contentPanel, "Center");
		backgroundPanel.add(buttonPanel, "South");
	}

	// 初始化label面板
	public void initLabelPanel() {
		namePanel = new JPanel();
		JLabel title = new JLabel("删除用户信息");
		title.setFont(MyFont.Static);
		namePanel.add(title);
	}

	
	public void initContentPanel() {
		contentPanel = new JPanel();

		label_user = new JLabel("用户名", JLabel.CENTER);
		
		userBox = new JComboBox<>();
		UserServiceImpl userService = new UserServiceImpl();
		//List<Object[]> list_user = null;
		try {
			list_user = userService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		userBox.addItem(new Item("请选择", "请选择").toString());
		if (list_user != null) {
			for (Object[] object : list_user) {
				userBox.addItem( new Item((String)object[0], (String)object[1]).toString() );
			}
		}
		//userBox.addActionListener(this);

		contentPanel.add(label_user);
		contentPanel.add(userBox);

		}

	// 初始化按钮面板
	public void initButtonPanel() {
		buttonPanel = new JPanel();

		button_delete = new JButton("删除");
		button_delete.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_delete.setForeground(Color.white);
		button_delete.setFont(MyFont.Static);
		button_delete.addMouseListener(this);

		buttonPanel.add(button_delete);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if("请选择".equals(userBox.getSelectedItem())) {
			JOptionPane.showMessageDialog(null, "请选择需要删除的用户");
		}
		else {
			//System.out.println(userBox.getSelectedItem());
			UserServiceImpl userservice = new UserServiceImpl();
			Object[] obj = {(Object)userBox.getSelectedItem()};
			try {
				int result = userservice.deleteUserByName(obj);;
				if(result > 0) {
					JOptionPane.showMessageDialog(null, "删除成功");
					this.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}catch (Exception exc) {
				// TODO: handle exception
				exc.getMessage();
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
