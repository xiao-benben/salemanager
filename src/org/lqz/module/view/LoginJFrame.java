package org.lqz.module.view;
//只改变了这一个类  在image中加了一个background.jpg
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.lqz.framework.util.ImagePanel;
import org.lqz.framework.util.MyFont;
import org.lqz.module.entity.User;
import org.lqz.module.services.Impl.UserServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class LoginJFrame extends JFrame implements MouseListener, FocusListener {

	private static final long serialVersionUID = 1L;

	// 全局的位置变量，用于表示鼠标在窗口上的位置
	static Point origin = new Point();

	// 定义全局组件
	JTextField username = new JTextField(20);
	JPasswordField password = new JPasswordField(20);
	ImagePanel backgroundPanel = null;
	JButton button_login, button_reset;

	public LoginJFrame() {
		
		//窗口淡入淡出
		//new WindowOpacity(this);
		//图像
		Image backgrounImage = null;
		try {
			//读取背景图片
			//backgrounImage = ImageIO.read(new File("image/loginbackground.png"));
			backgrounImage = ImageIO.read(new File("image/background.jpg"));
			Image imgae = ImageIO.read(new File("image/logo.png"));
			
			
			//为什么直接加在了左上角？？？？
			this.setIconImage(imgae);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 窗口背景面板
		backgroundPanel = new ImagePanel(backgrounImage);
		backgroundPanel.setLayout(null);
		/*x - 组件的新 x 坐标
		y - 组件的新 y 坐标
		width - 组件的新 width
		height - 组件的新 height*/
		//username.setBounds(230, 197, 173, 30);
		username.setBounds(120, 217, 373, 40);
	    username.setOpaque(false);// 设置控件透明
//		username.setBounds(240, 220, 173, 30);
		
		//设置字体，MyFont一个类
		username.setFont(MyFont.Static);
		
		
		//设置了焦点监视器  需要查看具体实现了什么功能，在那个位置
		username.addFocusListener(this);
		username.setText("用户名/账号");
		


		password.setBounds(120, 270, 373, 40);
		password.setOpaque(false);// 设置控件透明
//		password.setBounds(240, 290, 173, 30);
		password.setFont(MyFont.Static);
		password.addFocusListener(this);		
		password.setText("密码");
		//没啥用
		password.setEchoChar('\0');
		
		button_login = new JButton();
		button_login.setBounds(45, 320, 280, 40);
		button_login.setContentAreaFilled(false);
		

		
		
		
		
		//BEButtonUI?????

	//	button_login.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
		//设置按钮前景颜色
//		button_login.setForeground(Color.white);
	//	button_login.setFont(MyFont.Static);
		button_login.addMouseListener(this);
		//button_login.setOpaque(false);// 设置控件透明
		
/*		button_reset = new JButton("重置");
		button_reset.setBounds(330, 320, 70, 27);
//		button_reset.setBounds(340, 350, 70, 27);
		button_reset.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		button_reset.setForeground(Color.white);
		button_reset.setFont(MyFont.Static);
		button_reset.addMouseListener(this);
*/
		backgroundPanel.add(username);
		backgroundPanel.add(password);
		backgroundPanel.add(button_login);
	//	backgroundPanel.add(button_reset);
		
		this.add(backgroundPanel);
		this.setTitle("  商店销售管理系统");
		this.setSize(430, 530);
		this.setVisible(true);
		
		
		
		this.requestFocus(); // 把输入焦点放在调用这个方法的控件上
		//设置相对于括号中组件的位置
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//是否可调整大小
		this.setResizable(false);
		
	}

	// 鼠标点击事件  只针对于登录
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == button_login) {
			if ("用户名/账号".equals(username.getText())) {
				JOptionPane.showMessageDialog(null, "用户名不能为空");
			} else if ("密码".equals(password.getText())) {
				JOptionPane.showMessageDialog(null, "用户密码不能为空");
			} else {
				String params[] = { username.getText(), password.getText() };
			//用到了UserServiceImpl类
				UserServiceImpl userService = new UserServiceImpl();
				try {
					User user = userService.selectOne(params);
					if (user == null) {
						JOptionPane.showMessageDialog(null, "用户名密码有误");
					} else if (user.getIdentity().equals("1")) {
						this.setVisible(false);
						//用户密码验证正确   进入IndexJFrame界面
						new IndexJFrame(user);
					}
					else {
						this.setVisible(false);
						new EmployeeIndexJFrame(user);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}//重置功能
		} else if (e.getSource() == button_reset) {
			username.setText("用户名/账号");
			password.setText("密码");
			password.setEchoChar('\0');
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

	// 聚焦事件
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == username) {
			if (username.getText().equals("用户名/账号")) {
				username.setText("");
			}
		} else if (e.getSource() == password) {
			if (password.getText().equals("密码")) {
				password.setText("");
				password.setEchoChar('*');
			}
		}

	}

	// 失焦事件
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == username) {
			if (username.getText().equals("")) {
				username.setText("用户名/账号");
			}
		} else if (e.getSource() == password) {
			if (password.getText().equals("")) {
				password.setText("密码");
				password.setEchoChar('\0');
			}
		}
	}

	
}