package org.lqz.module.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.lqz.framework.util.BillNo;
import org.lqz.framework.util.Item;
import org.lqz.framework.util.MyFont;
import org.lqz.module.entity.User;
import org.lqz.module.services.Impl.*;


public class AddSaleOrderJFrame extends JFrame implements MouseListener, ActionListener {

	// 定义全局组件
	JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
	JLabel label_name, label_amount, label_category, label_warehouse, label_date;
	JComboBox select_name, select_category, select_warehouse;
	JTextField amount, date;
	JButton button_add;

	// 商品库存
	int goods_stock;

	// 获得屏幕的大小
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 表格对象
	JTable table;
	int selectedRow;
	SaleOrderManagerJPanel parentPanel;


	// 用户对象
	User user;

	public AddSaleOrderJFrame(User user, SaleOrderManagerJPanel parentPanel) {

		this.user = user;
		this.parentPanel = parentPanel;

		initBackgroundPanel();

		this.add(backgroundPanel);

		this.setTitle("添加销售单");
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

		JLabel title = new JLabel("销售信息");
		title.setFont(MyFont.Static);

		labelPanel.add(title);
	}

	// 初始化商品信息面板
	public void initContentPanel() {
		contentPanel = new JPanel(new GridLayout(5, 2));

		label_name = new JLabel("商品名称", JLabel.CENTER);
		label_amount = new JLabel("销售数量", JLabel.CENTER);
		label_date = new JLabel("销售日期", JLabel.CENTER);
		label_category = new JLabel("所属分类", JLabel.CENTER);
		label_warehouse = new JLabel("所属仓库", JLabel.CENTER);

		// 商品名称下拉框
		select_name = new JComboBox();
		GoodsServiceImpl goodsService = new GoodsServiceImpl();
		List<Object[]> list_goods = new ArrayList();
		try {
			list_goods = goodsService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		select_name.addItem(new Item("请选择", "请选择"));
		if (!list_goods.isEmpty()) {
			int sign = 0;
			for (Object[] object : list_goods) {

				select_name.addItem(new Item((String) object[0], (String) object[1]));
			}
		}
		select_name.addActionListener(this);

		amount = new JTextField("");
		date = new JTextField("");
		select_category = new JComboBox();
		select_category.setEnabled(false);
		select_warehouse = new JComboBox();
		select_warehouse.setEnabled(false);

		contentPanel.add(label_name);
		contentPanel.add(select_name);
		contentPanel.add(label_amount);
		contentPanel.add(amount);
		contentPanel.add(label_date);
		contentPanel.add(date);
		contentPanel.add(label_category);
		contentPanel.add(select_category);
		contentPanel.add(label_warehouse);
		contentPanel.add(select_warehouse);

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

	// 鼠标点击事件
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == button_add) {

			String amount_String = amount.getText().trim();
			String dateString = date.getText().trim();
			String name = ((Item) select_name.getSelectedItem()).getKey();
			if ("请选择".equals(name)) {
				JOptionPane.showMessageDialog(null, "请选择销售商品");
			} else if (amount_String.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入销售数量");
			} else if (dateString.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入销售日期");
			} else {
				int amount_int = Integer.valueOf(amount_String);
				if (amount_int > goods_stock) {
					JOptionPane.showMessageDialog(null, "商品库存不足");
				} else {
					int result = 0;
					String id = UUID.randomUUID().toString().replaceAll("-", "");
					String billno = BillNo.getBillNo();
					String handlerId = null;
					if (user != null) {
						handlerId = user.getUserId();
					}
					String warehouseId = ((Item) select_warehouse.getSelectedItem()).getKey();
					String categoryId = ((Item) select_category.getSelectedItem()).getKey();
					//Object[] params = { id, billno, handlerId, categoryId, warehouseId, amount_double, name };
					Object[] params = {id, billno, handlerId, warehouseId, categoryId, amount_int, name, dateString};
					SaleslistServiceImpl saleOrderService = new SaleslistServiceImpl();
					try {
						result = saleOrderService.insertSaleslist(params);  
						if(result > 0) {
							int tempresult = 0;
							GoodsServiceImpl goodsService = new GoodsServiceImpl();
							Object[] tempparams = { -1*amount_int, name };
							try {
								tempresult = goodsService.updateInventoryById(tempparams)   ;
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if(tempresult > 0) {
								JOptionPane.showMessageDialog(null, "添加成功");
								this.setVisible(false);
								parentPanel.refreshTablePanel();
							}
							else {
								JOptionPane.showMessageDialog(null, "添加失败");
							}
						}
					} catch (Exception e1) {
						e1.printStackTrace();
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
					int amount_int = (Integer) object[4];
					this.goods_stock = amount_int;
					String amount_String = String.valueOf(amount_int);
					amount.setText(amount_String);
					select_category.removeAllItems();
					select_category.addItem(new Item((String) object[0], (String) object[1]));
					select_warehouse.removeAllItems();
					select_warehouse.addItem(new Item((String) object[2], (String) object[3]));
				}
			}
		}
	}

}
