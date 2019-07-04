package org.lqz.module.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.*;

import org.lqz.framework.util.Id;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.lqz.framework.util.BillNo;
import org.lqz.framework.util.Item;
import org.lqz.framework.util.MyFont;
import org.lqz.module.entity.User;
import org.lqz.module.services.Impl.UserServiceImpl;

public class AddUserJFrame extends JFrame implements MouseListener {
	JPanel backgroundPanel, namePanel, contentPanel, buttonPanel;
	JLabel label_name, label_password, label_identity;
	JTextField nameField;
	JPasswordField passwordField;
	JComboBox<String> select_identity;
	JButton button_add;
	
	UserManagerJPanel parentPanel;
	// 获得屏幕的大小
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public AddUserJFrame(UserManagerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		initBackgroundPanel();

		this.add(backgroundPanel);

		this.setTitle("添加人员");
		this.setSize(400, 250);
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
		JLabel title = new JLabel("人员信息");
		title.setFont(MyFont.Static);
		namePanel.add(title);
	}

	public void initContentPanel() {
		contentPanel = new JPanel(new GridLayout(4, 2));

		label_name = new JLabel("用户名", JLabel.CENTER);
		label_password = new JLabel("密码", JLabel.CENTER);
		label_identity = new JLabel("身份", JLabel.CENTER);
		nameField = new JTextField(20);
		passwordField = new JPasswordField(20);
		
		select_identity = new JComboBox<String>();
		//String[] identityString = {"销售员", "管理员"};
		select_identity.addItem("销售员");
		select_identity.addItem("管理员");

		contentPanel.add(label_name);
		contentPanel.add(nameField);
		contentPanel.add(label_password);
		contentPanel.add(passwordField);
		contentPanel.add(label_identity);
		contentPanel.add(select_identity);

		}

	// 初始化按钮面板
	public void initButtonPanel() {
		buttonPanel = new JPanel();

		button_add = new JButton("保存");
		button_add.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_add.setForeground(Color.white);
		button_add.setFont(MyFont.Static);
		button_add.addMouseListener(this);

		buttonPanel.add(button_add);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == button_add) {
			if("".equals(nameField.getText())) {
				JOptionPane.showMessageDialog(null, "用户名不能为空");
			}
			else if("".equals(passwordField.getText().trim())) {
				JOptionPane.showMessageDialog(null, "密码不能为空");
			}
			else {
				int result = 0;
				UserServiceImpl userservice = new UserServiceImpl();
				String name = nameField.getText();
				List<Object[]> list_user = null;
				ArrayList<String> usersname = new ArrayList<>();
				try {
					list_user = userservice.selectAll();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				if (list_user != null) {
					for (Object[] obj : list_user) {
						usersname.add((String)obj[1]);
					}
				}
				try {
					if(usersname.contains(name)){
						JOptionPane.showMessageDialog(null, "用户名已存在");
						nameField.setText("");
						passwordField.setText("");
					}
					else {
						String password = passwordField.getText();
						String id = Id.getId();
						String identity_string = null;
						String identityString = select_identity.getSelectedItem().toString();
						if("销售员".equals(identityString)) {
							identity_string = "0";
						}
						else {
							identity_string = "1";
						}
						int identity = Integer.valueOf(identity_string);
						Object[] obj = {id, name, password, identity};
						try {
							result = userservice.insertUser(obj);
							
						} catch (Exception e2) {
							e2.getMessage();
						}
						if(result > 0) {
							JOptionPane.showMessageDialog(null, "添加成功");
							this.setVisible(false);
						}
						else {
							JOptionPane.showMessageDialog(null, "添加失败");
							this.setVisible(false);
						}
					}
				} catch (Exception e2) {
					e2.getMessage();
				}
				
				/*String password = passwordField.getText();
				String id = Id.getId();
				String identity_string = null;
				String identityString = select_identity.getSelectedItem().toString();
				if("销售员".equals(identityString)) {
					identity_string = "0";
				}
				else {
					identity_string = "1";
				}
				int identity = Integer.valueOf(identity_string);
				Object[] obj = {id, name, password, identity};
				try {
					result = userservice.insertUser(obj);
					
				} catch (Exception e2) {
					e2.getMessage();
				}
				if(result > 0) {
					JOptionPane.showMessageDialog(null, "添加成功");
					this.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "添加失败");
					this.setVisible(false);
				}*/
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
