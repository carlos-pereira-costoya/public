package com.lookiero.rover.engine;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class CommandBuilder {

	public static final int MIN_LENGTH = 3;
	public static final int X_POS = 0;
	public static final int Y_POS = 1;
	public static final int O_POS = 2;
	public static final int M_POS = 3;

	protected static int computeX(String command) throws NumberFormatException {
		return Integer.parseInt(command.substring(X_POS, X_POS + 1));
	}

	protected static int computeY(String command) throws NumberFormatException {
		return Integer.parseInt(command.substring(Y_POS, Y_POS + 1));
	}
	
	protected static Orientation computeO(String command) throws IllegalArgumentException {
		return Orientation.valueOf(command.substring(O_POS, O_POS + 1).toUpperCase());
	}

	protected static List<Movement> computeM(String command) throws IllegalArgumentException {
		List<Movement> l = new LinkedList<Movement>();
		
		if (command.length() > MIN_LENGTH) {
			String movs = command.substring(M_POS).toUpperCase();
		
			for (char m : movs.toCharArray()) {
				l.add(Movement.valueOf(String.valueOf(m)));
			}
		}
		return l;
	}

	public static Command compute(String command) throws NumberFormatException, IllegalArgumentException {
		if (command == null || command.length() < MIN_LENGTH) {
			throw new IllegalArgumentException();
		}
		return new Command(computeX(command), computeY(command), computeO(command), computeM(command));
	}
}
