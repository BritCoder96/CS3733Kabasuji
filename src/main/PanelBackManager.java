package main;

import java.awt.Container;
import java.util.Stack;
/**
 * Deals with the state of the view.
 * 
 * @author bhuchley
 *
 */
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
