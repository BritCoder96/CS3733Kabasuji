package controllers;

import models.Level;

import views.GameLossOverlay;
import views.GameScreen;
import views.GameWinOverlay;
import views.KabasujiFrame;

public class EndGameController {
	public EndGameController(Level level, KabasujiFrame frame, GameScreen gameScreen) {
		//Hide the previous screen
		gameScreen.setVisible(false);
		
		if (level.getHasWon()) {
			//Pass the frame to the new screen
			GameWinOverlay newPanel = new GameWinOverlay(level.getNumberOfStars(), frame);
			//Load the new screen
			newPanel.setVisible(true);
			
			//Loading the content of the new window.
			frame.setContentPane(newPanel);
		}
		else {
			//Pass the frame to the new screen
			GameLossOverlay newPanel = new GameLossOverlay(frame);
			//Load the new screen
			newPanel.setVisible(true);
			
			//Loading the content of the new window.
			frame.setContentPane(newPanel);
		}
		
		
	}
}
