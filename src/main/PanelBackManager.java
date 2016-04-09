package main;

import java.awt.Container;
import java.util.Stack;

public class PanelBackManager {
	Stack<Container> panels;
	
	public PanelBackManager() {
		panels = new Stack<Container>();
	}
	
	public void pushContainer(Container container) {
		panels.push(container);
	}
	
	public Container popContainerAndPeek() {
		panels.pop();
		return panels.peek();
	}
}
