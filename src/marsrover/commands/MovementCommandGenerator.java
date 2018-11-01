/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover.commands;


import java.util.ArrayList;
import java.util.List;
import marsrover.exceptions.InvalidCommandException;

/**
 *
 * @author A142400
 */
public class MovementCommandGenerator {
    public static List <ICommandMovement> generateCommands(String command) throws InvalidCommandException{
	List <ICommandMovement> commands = new ArrayList<>();
	for(int i=0; i<command.length();i++){
            switch(command.charAt(i)){
		case 'M':
		    commands.add(new MoveForward());
		    break;
		case 'L':
		    commands.add(new TurnLeft());
		    break;
		case 'R':
		    commands.add(new TurnRight());
		    break;
                case 'B':
		    commands.add(new Reverse());
		    break;
		default :
		    throw new InvalidCommandException(command.charAt(i) 
			    + " is not a valid command");
		    
	    }
        }
	return commands;
    }
}
