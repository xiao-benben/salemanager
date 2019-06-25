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
import org.lqz.module.entity.User;
import org.lqz.module.services.Impl.GoodsServiceImpl;
import org.lqz.module.services.Impl.SaleOrderServiceImpl;
import org.lqz.module.services.Impl.StockOrderServiceImpl;



//暂时没有实现接口
//implements MouseListener , ActionListener
	public class ModifyUserInfomationJFrame extends JFrame  {

		// 定义全局组件
		JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
		JLabel label_name, label_password, label_identify;
		JButton button_modify;
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

		public ModifyUserInfomationJFrame(User user, UserManagerJPanel parentPanel) {

			this.user = user;
			this.parentPanel = parentPanel;

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
				name.setText(user.getName());
				password.setText(user.getPassword());
				String identifyString = "";
				if (user.getIdentity().equals("1")) {
					identifyString = "管理员";
				} else if (user.getIdentity().equals("0")) {
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
	//		button_modify.addMouseListener(this);

			buttonPanel.add(button_modify);
		}

		// 鼠标点击事件
	//	@Override
	/*	public void mouseClicked(MouseEvent e) {
			if (e.getSource() == button_add) {

				String amount_String = amount.getText().trim();
				String name = ((Item) select_name.getSelectedItem()).getKey();
				if ("请选择".equals(name)) {
					JOptionPane.showMessageDialog(null, "请选择销售商品");
				} else if (amount_String.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入销售数量");
				} else {
					double amount_double = Double.valueOf(amount_String);
					if (amount_double > goods_stock) {
						JOptionPane.showMessageDialog(null, "商品库存不足");
					} else {
						int result = 0;
						String id = UUID.randomUUID().toString().replaceAll("-", "");
						String billno = BillNo.getBillNo();
						String handlerId = null;
						if (user != null) {
							handlerId = user.getId();
						}
						String warehouseId = ((Item) select_warehouse.getSelectedItem()).getKey();
						String categoryId = ((Item) select_category.getSelectedItem()).getKey();
						Object[] params = { id, billno, handlerId, categoryId, warehouseId, amount_double, name };
						SaleOrderServiceImpl saleOrderService = new SaleOrderServiceImpl();
						try {
							result = saleOrderService.insert(params);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						if (result > 0) {

							// 添加出库单
							int outputresult = 0;

							String outputId = UUID.randomUUID().toString().replaceAll("-", "");
							String outputBillno = BillNo.getBillNo();
							Object[] outputParams = { outputId, outputBillno, handlerId, warehouseId, categoryId,
									amount_double, name };
							StockOrderServiceImpl stockOrderService = new StockOrderServiceImpl();
							try {
								outputresult = stockOrderService.insertStockOutput(outputParams);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							if (outputresult > 0) {
								int tempresult = 0;
								GoodsServiceImpl goodsService = new GoodsServiceImpl();
								Object[] tempparams = { -amount_double, name };
								try {
									tempresult = goodsService.updateStockById(tempparams);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								if (tempresult > 0) {
									JOptionPane.showMessageDialog(null, "销售单添加成功");
									this.setVisible(false);
									
								//	parentPanel.refreshTablePanel();
								}
							}
						}
					}
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

		// 下拉框改变事件
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == select_name) {
				String id = ((Item) select_name.getSelectedItem()).getKey();
				Object[] params = { id };
				GoodsServiceImpl goodsService = new GoodsServiceImpl();
				List<Object[]> list_goods = new ArrayList();
				try {
					list_goods = goodsService.selectById(params);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (!list_goods.isEmpty()) {
					for (Object[] object : list_goods) {
						double amount_double = (Double) object[4];
						this.goods_stock = amount_double;
						String amount_String = String.valueOf(amount_double);
						amount.setText(amount_String);
						select_category.removeAllItems();
						select_category.addItem(new Item((String) object[0], (String) object[1]));
						select_warehouse.removeAllItems();
						select_warehouse.addItem(new Item((String) object[2], (String) object[3]));
					}
				}
			}
		}

		*/
		
		
		



}
