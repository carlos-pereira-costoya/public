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
public class EngineControllerTest {

	private String sCommand0 = null;
	private String sCommand1 = null;
	private String sCommand2 = null;
	
	private EngineController engineController = null;
	
	@Before
	public void setUp() {
		sCommand0 = "21N";
		sCommand1 = "21NFFRFRFFLF";
		sCommand2 = "21NFFFFBLRFF";
		
		engineController = new EngineControllerImpl();
	}
	
	@Test
	public void testNoChange() {
		Command input = CommandBuilder.compute(sCommand0);
		Command output = engineController.execute(input);
		assertTrue("Rover position", "21N".compareTo(output.toString()) == 0);
	}
	
	@Test
	public void test1WithChange() {
		Command input = CommandBuilder.compute(sCommand1);
		Command output = engineController.execute(input);
		assertTrue("Rover position", "60E".compareTo(output.toString()) == 0);
	}
	
	@Test
	public void test2WithChange() {
		Command input = CommandBuilder.compute(sCommand2);
		Command output = engineController.execute(input);
		assertTrue("Rover position", "31S".compareTo(output.toString()) == 0);
	}
}
