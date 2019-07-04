package org.lqz.module.view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.*;

import org.lqz.framework.util.ImagePanel;
import org.lqz.framework.util.MyFont;
import org.lqz.framework.util.WindowOpacity;
import org.lqz.main.Entrance;
import org.lqz.module.entity.User;

public class IndexJFrame extends JFrame implements MouseListener, ActionListener {

	// 定义用户对象
	private User user;

	// 定义辅助变量
	int sign_home = 0;
	int sign_stock = 0;
	int sign_purchase_sale_stock = 0;
	int sign_userManager = 0;

	// 获得屏幕的大小
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义全局组件
	JPanel backgroundPanel, topPanel, topMenu, topPrompt, centerPanel, subPanel, subMenu;
	JTabbedPane jTabbedPane;
	JButton logout;

	JLabel home, stock, purchase_sale_stock, userManager;

	public IndexJFrame(User user) {

		this.user = user;
		
		//窗口淡入淡出
		//new WindowOpacity(this);
		
		// 设置tab面板缩进
		UIManager.put("TabbedPane.tabAreaInsets", new javax.swing.plaf.InsetsUIResource(0, 0, 0, 0));

		try {
			Image imgae = ImageIO.read(new File("image/logo.png"));
			this.setIconImage(imgae);
		} catch (IOException e) {
			e.printStackTrace();
		}

		initBackgroundPanel();

		this.setTitle("商店销售管理系统");
		this.setSize((int) (width * 0.8f), (int) (height * 0.8f));
		this.setVisible(true);
		this.setLocationRelativeTo(null);	// 此窗口将置于屏幕的中央。
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// 初始化背景面板
	public void initBackgroundPanel() {

		backgroundPanel = new JPanel(new BorderLayout());
		initTop();
		initCenterPanel();

		backgroundPanel.add(topPanel, "North");
		backgroundPanel.add(centerPanel, "Center");

		this.add(backgroundPanel);
	}

	// 初始化顶部顶部面板
	public void initTop() {

		initTopMenu();
		initTopPrompt();

		topPanel = new JPanel(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(width, 40));

		topPanel.add(topMenu, "West");
		topPanel.add(topPrompt, "East");
	}

	// 初始化顶部菜单
	public void initTopMenu() {

		topMenu = new JPanel();
		topMenu.setPreferredSize(new Dimension(500, 40));
		topMenu.setOpaque(false);

		String[] nameStrings = { "首页", "商品信息", "进销存管理", "人员信息管理" };

		home = CreateMenuLabel(home, nameStrings[0], "home", topMenu);
		home.setName("home");
		stock = CreateMenuLabel(stock, nameStrings[1], "baseData", topMenu);
		stock.setName("stock");
		purchase_sale_stock = CreateMenuLabel(purchase_sale_stock, nameStrings[2], "purchase_sale_stock", topMenu);
		purchase_sale_stock.setName("purchase_sale_stock");
		userManager = CreateMenuLabel(userManager, nameStrings[3], "userManager", topMenu);
		userManager.setName("userManager");
	}

	// 创建顶部菜单Label
	public JLabel CreateMenuLabel(JLabel jlabel, String text, String name, JPanel jpanel) {
		JLabel line = new JLabel("<html>&nbsp;<font color='#D2D2D2'>|</font>&nbsp;</html>");
		Icon icon = new ImageIcon("image/" + name + ".png");
		jlabel = new JLabel(icon);
		jlabel.setText("<html><font color='black'>" + text + "</font>&nbsp;</html>");
		jlabel.addMouseListener(this);
		jlabel.setFont(MyFont.Static);
		jpanel.add(jlabel);
		if (!"userManager".equals(name)) {
			jpanel.add(line);
		}
		return jlabel;
	}

	// 初始化顶部欢迎面板
	public void initTopPrompt() {

		Icon icon = new ImageIcon("image/male.png");
		JLabel label = new JLabel(icon);
		if (user != null) {
			label.setText("<html><font color='black'>欢迎您，</font><font color='#336699'><b>" + this.user.getUserName()
					+ "</b></font></html>");
		} else {
			label.setText("<html><font color='black'>欢迎您，</font><font color='#336699'><b></b></font></html>");
		}
		label.setFont(MyFont.Static);
		
		logout = new JButton("注销");
		logout.addMouseListener(this);
		
		topPrompt = new JPanel();
		topPrompt.setPreferredSize(new Dimension(230,40));
		topPrompt.setOpaque(false);
		topPrompt.add(label);
		topPrompt.add(logout);
	}

	// 初始化中心面板
	public void initCenterPanel() {
		centerPanel = new JPanel(new BorderLayout());
		// "首页"两字变为蓝色
		home.setText("<html><font color='#336699' style='font-weight:bold'>" + "首页" + "</font>&nbsp;</html>");
		creatHome();
		centerPanel.setOpaque(false);// 设置控件透明
	}

	// 初始化辅助变量
	public void initSign() {
		sign_home = 0;
		sign_stock = 0;
		sign_purchase_sale_stock = 0;
		sign_userManager = 0;
	}

	// 创建首页面板
	public void creatHome() {

		centerPanel.removeAll();
		try {
			Image bgimg = ImageIO.read(new File("image/indexbackground.png"));
			ImagePanel centerBackground = new ImagePanel(bgimg);
			centerPanel.add(centerBackground, "Center");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 创建商品信息面板
	public void creatstockTab() {

		centerPanel.removeAll();
		// 设置tab标题位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置tab布局
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.Static);

		jTabbedPane.addTab("查询商品", new GoodsManagerJPanel().backgroundPanel);
		centerPanel.add(jTabbedPane, "Center");
	}

	// 创建进存管理面板
	public void creatpurchaseSaleStockTab() {

		centerPanel.removeAll();
		// 设置tab标题位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置tab布局
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.Static);

		jTabbedPane.addTab("销售单", new SaleOrderManagerJPanel(user).backgroundPanel);
		jTabbedPane.addTab("入库单", new StockInputManagerJPanel(user).backgroundPanel);
		
		jTabbedPane.addTab("仓库管理", new WarehouseManagerJPanel().backgroundPanel);

		centerPanel.add(jTabbedPane, "Center");
	}

	// 创建用户管理面板
	public void creatUserManagerTab() {

		centerPanel.removeAll();
		// 设置tab标题位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置tab布局
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.Static);

	//	jTabbedPane.addTab("用户管理", new EmployeeUserManagerJPanel(user, this).backgroundPanel);
		jTabbedPane.addTab("用户管理", new UserManagerJPanel(user,this).backgroundPanel);
		centerPanel.add(jTabbedPane, "Center");
	}

	// 鼠标点击事件
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == home) {
			initSign();
			sign_home = 1;
			creatHome();
			home.setText("<html><font color='#336699' style='font-weight:bold'>" + "首页" + "</font>&nbsp;</html>");
			stock.setText("<html><font color='black'>" + "商品信息" + "</font>&nbsp;</html>");
			purchase_sale_stock.setText("<html><font color='black'>" + "进销存管理" + "</font>&nbsp;</html>");
			userManager.setText("<html><font color='black'>" + "人员信息管理" + "</font>&nbsp;</html>");
		} else if (e.getSource() == stock) {
			initSign();
			sign_stock = 1;
			creatstockTab();
			stock.setText("<html><font color='#336699' style='font-weight:bold'>" + "商品信息" + "</font>&nbsp;</html>");
			home.setText("<html><font color='black'>" + "首页" + "</font>&nbsp;</html>");
			purchase_sale_stock.setText("<html><font color='black'>" + "进销存管理" + "</font>&nbsp;</html>");
			userManager.setText("<html><font color='black'>" + "人员信息管理" + "</font>&nbsp;</html>");
		} else if (e.getSource() == purchase_sale_stock) {
			initSign();
			sign_purchase_sale_stock = 1;
			creatpurchaseSaleStockTab();
			purchase_sale_stock.setText(
					"<html><font color='#336699' style='font-weight:bold'>" + "进销存管理" + "</font>&nbsp;</html>");
			home.setText("<html><font color='black'>" + "首页" + "</font>&nbsp;</html>");
			stock.setText("<html><font color='black'>" + "商品信息" + "</font>&nbsp;</html>");
			userManager.setText("<html><font color='black'>" + "人员信息管理" + "</font>&nbsp;</html>");
		} else if (e.getSource() == userManager) {
			initSign();
			sign_userManager = 1;
			creatUserManagerTab();
			userManager.setText("<html><font color='#336699' style='font-weight:bold'>" + "人员信息管理" + "</font>&nbsp;</html>");
			home.setText("<html><font color='black'>" + "首页" + "</font>&nbsp;</html>");
			stock.setText("<html><font color='black'>" + "商品信息" + "</font>&nbsp;</html>");
			purchase_sale_stock.setText("<html><font color='black'>" + "进销存管理" + "</font>&nbsp;</html>");
		} else if(e.getSource() == logout){
			this.setVisible(false);
			Entrance.main(null);
		} else {
			System.out.println("ok");
		}

	}

	// 鼠标划入事件
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == home) {
			// 鼠标改变形状
			home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			home.setText("<html><font color='#336699' style='font-weight:bold'>" + "首页" + "</font>&nbsp;</html>");
		} else if (e.getSource() == stock) {
			stock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			stock.setText("<html><font color='#336699' style='font-weight:bold'>" + "商品信息" + "</font>&nbsp;</html>");
		} else if (e.getSource() == purchase_sale_stock) {
			purchase_sale_stock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			purchase_sale_stock.setText("<html><font color='#336699' style='font-weight:bold'>" + "进销存管理" + "</font>&nbsp;</html>");
		} else if (e.getSource() == userManager) {
			userManager.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			userManager.setText("<html><font color='#336699' style='font-weight:bold'>" + "人员信息管理" + "</font>&nbsp;</html>");
		}

	}

	// 鼠标划出事件
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == home) {
			if (sign_home == 0) {
				home.setText("<html><font color='black'>" + "首页" + "</font>&nbsp;</html>");
			}
		} else if (e.getSource() == stock) {
			if (sign_stock == 0) {
				stock.setText("<html><font color='black'>" + "商品信息" + "</font>&nbsp;</html>");
			}
		} else if (e.getSource() == purchase_sale_stock) {
			if (sign_purchase_sale_stock == 0) {
				purchase_sale_stock.setText("<html><font color='black'>" + "进销存管理" + "</font>&nbsp;</html>");
			}
		} else if (e.getSource() == userManager) {
			if (sign_userManager == 0) {
				userManager.setText("<html><font color='black'>" + "人员信息管理" + "</font>&nbsp;</html>");
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
}
