package com.lookiero.rover.engine;

import java.util.List;

/**
 * Rover internal command.
 * 
 * @author pecojo
 */
public class Command {

	protected int x;
	protected int y;
	protected Orientation orientation;
	protected List<Movement> movements;

	public Command() {
		this(-1, -1, null, null);
	}

	public Command(int x, int y, Orientation orientation) {
		this(x, y, orientation, null);
	}
	
	public Command(int x, int y, Orientation orientation, List<Movement> movements) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
		this.movements = movements;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}
	
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append(x);
		s.append(y);
		s.append(orientation);
		
		if (movements != null && !movements.isEmpty()) {
			for (Movement mov : movements) {
				s.append(mov.toString());
			}
		}
		return s.toString();
	}
}
