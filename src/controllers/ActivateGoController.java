package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.NewLevel;

public class ActivateGoController implements ActionListener{

	NewLevel nlevel;
	
	public ActivateGoController(NewLevel nlevel){
		this.nlevel = nlevel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		nlevel.invokeNextScreen();
	}

}
