/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import marsrover.exceptions.InvalidCommandException;
import marsrover.exceptions.InvalidPositionException;
import marsrover.exceptions.InvalidTestcaseFile;
import marsrover.factory.RoverFactory;
import marsrover.geometry.Grid;
import marsrover.roverloader.RoverTestcaseLoader;
import marsrover.vehicles.Rover;
import marsrover.vehicles.RoverHandler;

/**
 *
 * @author paulbright
 */
public class RoverTestcase implements IRoverTestcase{

    private Object[][] data = null;
    private List<String> expected_results;
    private final List<String> testcase_load_results;
    private boolean bLoadFileTest = false;
    
    public RoverTestcase(String testcaseFile, String testcaseResultFile) throws IOException{
        this.expected_results = new ArrayList<>();
        this.testcase_load_results = new ArrayList<>();
        bLoadFileTest = true;
        loadTests(testcaseFile, testcaseResultFile);
    }
    public RoverTestcase(Object[][] data, List<String> expected_results){
        this.expected_results = new ArrayList<>();
        this.testcase_load_results = new ArrayList<>();
        bLoadFileTest = false;
        this.data = data;
        this.expected_results = expected_results;
    }

    private boolean testFileLoad(){
        if( testcase_load_results.size() > 0) {
            return checkExceptionResults(expected_results, testcase_load_results);            
        }
        
        List<String> received_results = RoverHandler.getInstance().animateVehicles();
        return checkResults(expected_results, received_results);        
    }
    @Override
    public boolean test() {
        if(bLoadFileTest) return testFileLoad(); 
        
        List<String> received_results = new ArrayList<>();
        try{
            loadRovers(data);
        }        
        catch(InvalidPositionException | InvalidCommandException ex){
            received_results.add(ex.getMessage());
        }
        catch (Exception ex){
            received_results.add(ex.getMessage());
        }
        
        if(received_results.size() > 0){
            return checkExceptionResults(expected_results, received_results);
        }
        received_results = RoverHandler.getInstance().animateVehicles();
        return checkResults(expected_results, received_results);
    }
    
     private void loadRovers(Object[][] data) throws InvalidPositionException, InvalidCommandException {

        Grid.initGrid((int) data[0][0], (int) data[0][1]);
        for (int i = 1; i < data.length; i++) {
            Rover rover;
            rover = (Rover) (new RoverFactory()).makeVehicle("", (int) data[i][0],
                    (int) data[i][1], (String)data[i][2],
                    (String) data[i][3]);
            Grid.addRover(rover);
        }
    }
     
    private boolean checkResults(List<String> expected_results, List<String> received_results) {
        if (expected_results.size() != received_results.size()) {
            return false;
        }
        for (int i = 0; i < expected_results.size(); i++) {
            if (expected_results.get(i).compareTo(received_results.get(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkExceptionResults(List<String> expected_results, List<String> received_results) {
        //System.out.println(received_results);
        if (expected_results.size() != received_results.size()) {
            return false;
        }
        for (int i = 0; i < expected_results.size(); i++) {
            if (!received_results.get(i).contains(expected_results.get(i))) {
                return false;
            }
        }
        return true;
    }
    private void loadTests(String testcaseFile, String testcaseResultFile) throws IOException{        
        try {
            RoverTestcaseLoader.initTestcase(testcaseFile);
        } catch (InvalidTestcaseFile | InvalidPositionException | InvalidCommandException ex) {
           testcase_load_results.add(ex.getMessage());
        }        
        RoverTestcaseLoader.loadFile(testcaseResultFile, expected_results);        
    }
    
    public static boolean isInteger(Object object) {
	if(object instanceof Integer) {
		return true;
	} else {
		String string = object.toString();
		
		try {
			Integer.parseInt(string);
		} catch(Exception e) {
			return false;
		}	
	}
  
    return true;
    }

}
