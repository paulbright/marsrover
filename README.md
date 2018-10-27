executing MarsRover and TestRovers 

java -cp marsrover.jar marsrover.MarsRover
java -cp marsrover.jar marsrover.MarsRover ../testcases/testcase_int_limit.txt
java -cp marsrover.jar test.TestRovers
java -cp marsrover.jar test.TestRovers ../testcases/testcase_int_limit.txt ../testcases/testcase_int_limit_results.txt 

running junit tests

java -cp ".:hamcrest-core-1.3.jar:junit-4.12.jar" org.junit.runner.JUnitCore test.JUnitTest

further improvements 

1. convert Grid to non-static. Currently it is kept static since the use case 
	implies one to many relationship between Grid and Rovers.
2. use JUnit for testing. It is not used at the moment to keep dependencies minimal.   