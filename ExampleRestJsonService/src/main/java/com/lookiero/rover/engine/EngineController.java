package com.lookiero.rover.engine;

/**
 * Controls the movements of the rover.
 * 
 * @author pecojo
 */
public interface EngineController {

	public Command execute(Command command);
}
