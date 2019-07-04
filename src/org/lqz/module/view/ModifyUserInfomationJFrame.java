package org.lqz.module.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.lqz.framework.util.BillNo;
import org.lqz.framework.util.Item;
import org.lqz.framework.util.MyFont;
import org.lqz.main.Entrance;
import org.lqz.module.entity.User;
import org.lqz.module.services.Impl.GoodsServiceImpl;
import org.lqz.module.services.Impl.*;
import org.lqz.module.services.Impl.UserServiceImpl;



//暂时没有实现接口
//implements MouseListener , ActionListener
	public class ModifyUserInfomationJFrame extends JFrame implements MouseListener {

		// 定义全局组件
		JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
		JLabel label_name, label_password, label_identify;
		JButton button_modify,button_save;
		JTextField name = new JTextField(10);
		JPasswordField password = new JPasswordField(10);
		JTextField identify = new JTextField(10);

		// 商品库存
		double goods_stock;

		// 获得屏幕的大小
		final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

		// 表格对象
		JTable table;
		int selectedRow;
		UserManagerJPanel parentPanel;


		// 用户对象
		User user;
		JFrame jframe;

		public ModifyUserInfomationJFrame(User user, UserManagerJPanel parentPanel,JFrame jframe) {

			this.user = user;
			this.parentPanel = parentPanel;
			this.jframe = jframe;

			initBackgroundPanel();

			this.add(backgroundPanel);

			this.setTitle("修改个人信息");
			this.setSize(480,270);
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

			backgroundPanel.add(labelPanel, "North");
			backgroundPanel.add(contentPanel, "Center");
			backgroundPanel.add(buttonPanel, "South");
		}

		// 初始化label面板
		public void initLabelPanel() {

			labelPanel = new JPanel();

			JLabel title = new JLabel("用户信息");
			title.setFont(MyFont.Static);

			labelPanel.add(title);
		}

		// 初始化商品信息面板
		public void initContentPanel() {
			contentPanel = new JPanel(new GridLayout(3, 2));

			label_name = new JLabel("用户名", JLabel.CENTER);
			label_name.setFont(MyFont.Static);
			label_password = new JLabel("密码", JLabel.CENTER);
			label_password.setFont(MyFont.Static);
			label_identify = new JLabel("身份", JLabel.CENTER);
			label_identify.setFont(MyFont.Static);
			if (user != null) {
				name.setText(user.getUserName());
				password.setText(user.getUserPassword());
				String identifyString = "";
				if (user.getUserIdentity() == 1) {
					identifyString = "管理员";
				} else if (user.getUserIdentity() == 0) {
					identifyString = "普通员工";
				}
				identify.setText(identifyString);
			}
			name.setFont(MyFont.Static);
			name.setEditable(false);
			password.setFont(MyFont.Static);
			password.setEditable(false);
			identify.setFont(MyFont.Static);
			identify.setEditable(false);


			contentPanel.add(label_name);
			contentPanel.add(name);
			contentPanel.add(label_password);
			contentPanel.add(password);
			contentPanel.add(label_identify);
			contentPanel.add(identify);


		}

		// 初始化按钮面板
		public void initButtonPanel() {
			buttonPanel = new JPanel();

			button_modify = new JButton("修改信息");
			button_modify.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
			button_modify.setForeground(Color.white);
			button_modify.setFont(MyFont.Static);
			button_modify.addMouseListener(this);

			buttonPanel.add(button_modify);
		}
		public void modifyUserContentPanel() {

			name.setEditable(true);
			password.setEditable(true);

			button_save = new JButton("保存修改");
			button_save.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
			button_save.setForeground(Color.white);
			button_save.setFont(MyFont.Static);
			button_save.addMouseListener(this);

			buttonPanel.removeAll();
			buttonPanel.add(button_save);

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == button_modify) {
				/**
				 * parentComponent 对话框所在的容器 
					message 提示消息 
					title 标题 
					optionType 选择按钮类型 
					JOptionPane.showConfirmDialog(parentComponent, message, title, optionType) 
				 */
				String input_password = (String) JOptionPane.showInputDialog(null, "请输入原始密码", "用户验证",
						JOptionPane.PLAIN_MESSAGE);
				if (input_password.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "原始密码不能为空");
				} else {
					if (user != null) {
						if (!input_password.equals(user.getUserPassword())) {
							JOptionPane.showMessageDialog(null, "原始密码有误");
						} else {
							JOptionPane.showMessageDialog(null, "验证通过，请您修改信息");
							modifyUserContentPanel();
						}
					} else {
						JOptionPane.showMessageDialog(null, "登录超时，请您重新登录");
					//	parentPanel.setVisible(false);
						Entrance.main(null);
					}
				}
			}
			if (e.getSource() == button_save) {
				String string_username = name.getText().trim();
				String string_password = password.getText().trim();
				if (string_username.isEmpty()) {
					JOptionPane.showMessageDialog(null, "用户名不能为空");
				} else if (string_password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "用户密码不能为空");
				} else {
					String params[] = { name.getText(), password.getText(), user.getUserId() };
					UserServiceImpl userService = new UserServiceImpl();
					int result = 0;
					try {
						result = userService.updateUserById(params);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (result > 0) {
						JOptionPane.showMessageDialog(null, "用户信息修改成功,请您重新登陆");
						this.setVisible(false);
						jframe.setVisible(false);
						Entrance.main(null);
					}
				}

			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	
		



}
