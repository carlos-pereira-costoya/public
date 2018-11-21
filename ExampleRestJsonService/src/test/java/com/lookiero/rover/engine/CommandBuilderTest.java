package com.lookiero.rover.engine;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lookiero.rover.controller.ApplicationInitializer;

/**
 * Rover engine tests
 * 
 * @author pecojo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ApplicationInitializer.class})
@WebAppConfiguration
public class CommandBuilderTest {

	private String sCommand = null;
	
	private String sErrorCommand0 = null;
	private String sErrorCommand1 = null;
	private String sErrorCommand2 = null;
	private String sErrorCommand3 = null;
	
	@Before
	public void setUp() {
		sCommand = "21NFFRFRFFLF";

		sErrorCommand0 = "21";
		sErrorCommand1 = "X1F";
		sErrorCommand2 = "21X";
		sErrorCommand3 = "21NW";
	}

	@Test
	public void testCommand() {
		Command output = CommandBuilder.compute(sCommand);
		assertTrue(sCommand.compareTo(output.toString()) == 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testShortCommand() {
		CommandBuilder.compute(sErrorCommand0);
	}
	
	@Test(expected=NumberFormatException.class)
	public void testInvalidPosition() {
		CommandBuilder.compute(sErrorCommand1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidOrientation() {
		CommandBuilder.compute(sErrorCommand2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidMovement() {
		CommandBuilder.compute(sErrorCommand3);
	}
}
