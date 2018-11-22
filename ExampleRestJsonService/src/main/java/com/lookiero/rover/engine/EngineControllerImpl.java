package com.lookiero.rover.engine;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Controls the movements of the rover.
 * 
 * @author pecojo
 */
@Component
public class EngineControllerImpl implements EngineController {

	protected final static Logger logger = Logger.getLogger(EngineControllerImpl.class);
	
	/**
	 * New increments at x-axis by orientation and movement.
	 */
	protected static int [] [] X_COORD_BY_ORIENTxMOV = {
		{ 0,  0, -1,  1},
		{ 0,  0,  1, -1},
		{ 1, -1,  0,  0},
		{-1,  1,  0,  0},
	};

	/**
	 * New increments at y-axis by orientation and movement.
	 */
	protected static int [] [] Y_COORD_BY_ORIENTxMOV = {
		{ 1, -1,  0,  0},
		{-1,  1,  0,  0},
		{ 0,  0,  1, -1},
		{ 0,  0, -1,  1},
	};
	
	public Command execute(Command command) {
		logger.info(String.format("Rover processing command %s", command.toString()));
		
		List<Movement> movs = command.getMovements();
		
		int currentX = command.getX();
		int currentY = command.getY();
		Orientation currentO = command.getOrientation();
		
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Rover at x: %s, y: %d, o: %s", 
				currentX, currentY, currentO.toString()));
		}
		
		if (movs != null) {
			for (Movement mov : movs) {
				int incX = X_COORD_BY_ORIENTxMOV[currentO.ordinal()][mov.ordinal()];
				int incY = Y_COORD_BY_ORIENTxMOV[currentO.ordinal()][mov.ordinal()];
				
				currentX += incX;
				currentY += incY;
				
				if (incX != 0) {
					currentO = incX > 0 ? Orientation.E : Orientation.W;
				} else if (incY != 0) {
					currentO = incY > 0 ? Orientation.N : Orientation.S;
				}
				
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Rover at x: %s, y: %d, o: %s", 
						currentX, currentY, currentO.toString()));
				}
			}
		}
		return new Command(currentX, currentY, currentO);
	}
}
