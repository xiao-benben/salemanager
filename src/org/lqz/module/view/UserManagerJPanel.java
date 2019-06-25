package org.lqz.module.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;

import org.lqz.framework.util.BaseTableModule;
import org.lqz.framework.util.Tools;
import org.lqz.module.entity.User;
import org.lqz.module.services.Impl.WarehouseServiceImpl;

/**
 * 用户管理功能面板
 */

public class UserManagerJPanel implements MouseListener  {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel,tablePanel1;
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	JLabel tool_add, tool_modify, tool_delete;
	User user = null;
	public UserManagerJPanel(User user) {
		this.user = user;
		backgroundPanel = new JPanel(new BorderLayout());

		initTopPanel();
		initTablePanel();
		initTablePanel1();
		
	}

	// 初始化顶部面板
	public void initTopPanel() {

		topPanel = new JPanel(new BorderLayout());

		initToolPanel();

		backgroundPanel.add(topPanel, "North");

	}

	// 初始化工具面板
	public void initToolPanel() {

		toolPanel = new JPanel();
		// 工具图标
		Icon icon_add = new ImageIcon("image/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("增加人员");
	//	tool_add.addMouseListener(this);

		Icon icon_modify = new ImageIcon("image/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("修改个人信息");
		tool_modify.addMouseListener(this);

		Icon icon_delete = new ImageIcon("image/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除人员");
	//	tool_delete.addMouseListener(this);

		toolPanel.add(tool_add);
		toolPanel.add(tool_modify);
		toolPanel.add(tool_delete);

		topPanel.add(toolPanel, "West");

	}

	// 初始化数据表格面板
	public void initTablePanel() {

		String params[] = {"用户ID",  "日期", "姓名", "销售额" };
	//	WarehouseServiceImpl warehouseService = new WarehouseServiceImpl();
		Vector<Vector> vector = new Vector<Vector>();
		try {
	//		vector = warehouseService.selectAllVexctor();
		} catch (Exception e) {
			e.printStackTrace();
		}

		baseTableModule = new BaseTableModule(params, vector);

		table = new JTable(baseTableModule);
		Tools.setTableStyle(table);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table.getColumnModel();// 获取列模型
		dcm.getColumn(0).setMinWidth(0); // 将第一列的最小宽度、最大宽度都设置为0
		dcm.getColumn(0).setMaxWidth(0);

		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);

		tablePanel.add(jScrollPane);

		backgroundPanel.add(tablePanel, "Center");
	}
	public void initTablePanel1() {

		String params[] = {"用户ID",  "日期", "利润",  };
	//	WarehouseServiceImpl warehouseService = new WarehouseServiceImpl();
		Vector<Vector> vector = new Vector<Vector>();
		try {
	//		vector = warehouseService.selectAllVexctor();
		} catch (Exception e) {
			e.printStackTrace();
		}

		baseTableModule = new BaseTableModule(params, vector);

		table = new JTable(baseTableModule);
		Tools.setTableStyle(table);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table.getColumnModel();// 获取列模型
		dcm.getColumn(0).setMinWidth(0); // 将第一列的最小宽度、最大宽度都设置为0
		dcm.getColumn(0).setMaxWidth(0);

		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel1 = new JPanel(new BorderLayout());
		tablePanel1.setOpaque(false);

		tablePanel1.add(jScrollPane);

		backgroundPanel.add(tablePanel1, "South");
	}

	// 更新数据表格
	/*public void refreshTablePanel() {

		backgroundPanel.remove(tablePanel);

		String params[] = { "仓库id", "序号", "仓库名称" };
		WarehouseServiceImpl warehouseService = new WarehouseServiceImpl();
		Vector<Vector> vector = new Vector<Vector>();
		try {
			vector = warehouseService.selectAllVexctor();
		} catch (Exception e) {
			e.printStackTrace();
		}

		baseTableModule = new BaseTableModule(params, vector);

		table = new JTable(baseTableModule);
		Tools.setTableStyle(table);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table.getColumnModel();// 获取列模型
		dcm.getColumn(0).setMinWidth(0); // 将第一列的最小宽度、最大宽度都设置为0
		dcm.getColumn(0).setMaxWidth(0);

		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);

		tablePanel.add(jScrollPane);

		backgroundPanel.add(tablePanel, "Center");
		backgroundPanel.validate();
	}*/

	// 鼠标点击事件
//	@Override
	public void mouseClicked(MouseEvent e) {
	//	if (e.getSource() == tool_add) {
	//		new AddWarehouseJFrame(this);
	//	}
		
		if (e.getSource() == tool_modify) {
			new ModifyUserInfomationJFrame(user, this);
	//	new	 EmployeeUserManagerJPanel();
		
	//	 new EmployeeUserManagerJPanel(user, EmployeeUserManagerJFrame).backgroundPanel);

		} /*else if (e.getSource() == tool_delete) {
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择仓库");
			} else {
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "是否确定删除？", "用户提示", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					String[] params = { id };
					WarehouseServiceImpl warehouseService = new WarehouseServiceImpl();
					try {
						int tempresult = warehouseService.deleteById(params);
						if (tempresult > 0) {
							JOptionPane.showMessageDialog(null, "仓库删除成功！");
							refreshTablePanel();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}*/
	}

	// 鼠标划入事件
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == tool_add) {
			tool_add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else if (e.getSource() == tool_modify) {
			tool_modify.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else if (e.getSource() == tool_delete) {
			tool_delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
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

	
