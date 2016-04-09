package views;

import java.awt.Container;

import javax.swing.JFrame;

import main.PanelBackManager;

public class KabasujiFrame extends JFrame {
	PanelBackManager backMgr;
	
	public KabasujiFrame(PanelBackManager backMgr) {
		super();
		this.backMgr = backMgr;
	}
	
	@Override
	public void setContentPane(Container contentPane) {
		super.setContentPane(contentPane);
		backMgr.pushContainer(contentPane);
	}
	
	public void returnToLastContentPane() {
		super.setContentPane(backMgr.popContainerAndPeek());
		getContentPane().setVisible(true);
	}
}
