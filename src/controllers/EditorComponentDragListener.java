package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import views.LevelEditor;

/**
 * Drag listener for components on the editor screen. It just forwards the drag events to the 
 * editor screen, with the coordinates corrected to be relative to the screen.
 * @author bhuchley
 *
 */
public class EditorComponentDragListener implements MouseMotionListener {
	/** The editor to inform of drag events */
	LevelEditor editor;
	/** The component that this listener is on */
	JComponent component;
	
	/**
	 * Make a new EditorComponentDragListener that watches the given component and passes events
	 * on to the given editor.
	 * @param editor the editor to pass the drag events to
	 * @param component the component that this listener is watching
	 */
	public EditorComponentDragListener(LevelEditor editor, JComponent component) {
		this.editor = editor;
		this.component = component;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		e.translatePoint(component.getX(), component.getY());
		editor.dispatchEvent(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		e.translatePoint(component.getX(), component.getY());
		editor.dispatchEvent(e);

	}

}
