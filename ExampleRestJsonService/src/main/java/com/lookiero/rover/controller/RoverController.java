package com.lookiero.rover.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lookiero.rover.engine.Command;
import com.lookiero.rover.engine.CommandBuilder;
import com.lookiero.rover.engine.EngineController;

/**
 * Rover REST controller.
 * 
 * @author pecojo
 */
@RestController
@RequestMapping("/controller")
public class RoverController {

	protected final static Logger logger = Logger.getLogger(RoverController.class);
	
	@Autowired private EngineController engineController;
	
	@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason = "Invalid command")
	@ExceptionHandler(value= {IllegalArgumentException.class, NumberFormatException.class})
	public void exceptionHandler() {
		
	}
	
    @RequestMapping(value = "/move", method = RequestMethod.PUT)
    public String move(@RequestParam(value = "command") String command) 
    		throws IllegalArgumentException, NumberFormatException {
    	if (command == null || command.isEmpty()) {
    		throw new IllegalArgumentException("command");
    	}

    	logger.info(String.format("New command %s", command));
    	
    	Command input = CommandBuilder.compute(command);
        Command output = engineController.execute(input);
        
        logger.info(String.format("New position %s", output));
        return output.toString();
    }
}