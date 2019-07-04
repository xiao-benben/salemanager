package org.lqz.module.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.lqz.framework.util.MyFont;
import org.lqz.module.services.Impl.WarehouseServiceImpl;

public class AddWarehouseJFrame extends JFrame implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
	JLabel label_name, label_location;
	JTextField name, location;
	JButton button_add;

	// 获得屏幕的大小
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 父面板对象
	WarehouseManagerJPanel parentPanel;

	public AddWarehouseJFrame(WarehouseManagerJPanel parentPanel) {
		this.parentPanel = parentPanel;

		initBackgroundPanel();

		this.add(backgroundPanel);

		this.setTitle("添加仓库");

		this.setSize(320, 210);
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

		JLabel title = new JLabel("仓库信息");
		title.setFont(MyFont.Static);

		labelPanel.add(title);
	}

	// 初始化商品信息面板
	public void initContentPanel() {
		contentPanel = new JPanel(new GridLayout(2, 2));

		label_name = new JLabel("仓库名称", JLabel.CENTER);
		label_location = new JLabel("仓库所在地", JLabel.CENTER);

		name = new JTextField("");
		location = new JTextField("");

		contentPanel.add(label_name);
		contentPanel.add(name);
		contentPanel.add(label_location);
		contentPanel.add(location);

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

			String name_String = name.getText().trim();
			String location_String = location.getText().trim();

			if (name_String.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入仓库名称");
			} else if (location_String.isEmpty()){
				JOptionPane.showMessageDialog(null, "请输入仓库所在地");
			} else {
				int result = 0;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				Object[] params = { id, name_String, location_String};
				WarehouseServiceImpl warehouseService = new WarehouseServiceImpl();
				try {
					result = warehouseService.insertById(params);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "仓库添加成功");
					this.setVisible(false);
					parentPanel.refreshTablePanel();
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

}
