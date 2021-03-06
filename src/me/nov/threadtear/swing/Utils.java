package me.nov.threadtear.swing;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class Utils {
	public static JPanel addTitleAndBorder(String title, Component c) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(title));
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(c, BorderLayout.CENTER);
		panel2.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.add(panel2, BorderLayout.CENTER);
		return panel;
	}

	public static void moveTreeItem(JTree tree, int direction) {
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		MutableTreeNode moveNode = (MutableTreeNode) tree.getLastSelectedPathComponent();
		if (moveNode == null)
			return;
		MutableTreeNode parent = (MutableTreeNode) moveNode.getParent();
		if (parent == null)
			return;
		int targetIndex = model.getIndexOfChild(parent, moveNode) + direction;
		if (targetIndex < 0 || targetIndex >= parent.getChildCount())
			return;
		model.removeNodeFromParent(moveNode);
		model.insertNodeInto(moveNode, parent, targetIndex);
		// make the node visible by scroll to it
		TreeNode[] nodes = model.getPathToRoot(moveNode);
		TreePath path = new TreePath(nodes);
		tree.scrollPathToVisible(path);
		// select the newly added node
		tree.setSelectionPath(path);
	}
}
