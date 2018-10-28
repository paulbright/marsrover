executing MarsRover and TestRovers 

java -cp marsrover.jar marsrover.MarsRover
java -cp marsrover.jar marsrover.MarsRover ../testcases/testcase_int_limit.txt
java -cp marsrover.jar test.TestRovers
java -cp marsrover.jar test.TestRovers ../testcases/testcase_int_limit.txt ../testcases/testcase_int_limit_results.txt 

running junit tests

java -cp ".:marsrover.jar:mockito-all-1.9.5.jar:hamcrest-core-1.3.jar:junit-4.12.jar" org.junit.runner.JUnitCore test.GridAndFactoryTest
java -cp ".:marsrover.jar:mockito-all-1.9.5.jar:hamcrest-core-1.3.jar:junit-4.12.jar" org.junit.runner.JUnitCore test.RoverHandlerTest
java -cp ".:marsrover.jar:mockito-all-1.9.5.jar:hamcrest-core-1.3.jar:junit-4.12.jar" org.junit.runner.JUnitCore test.RoverTest
java -cp ".:marsrover.jar:mockito-all-1.9.5.jar:hamcrest-core-1.3.jar:junit-4.12.jar" org.junit.runner.JUnitCore test.RoverTestcaseLoaderTest

further improvements 

1. convert Grid to non-static. Currently it is kept static since the use case 
	implies one to many relationship between Grid and Rovers.
2. add more JUnit unit tests   