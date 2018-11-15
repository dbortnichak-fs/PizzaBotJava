- the project has been built using java 8
- extract the tarball into the folder on your computer this will create a folder called PizzaBot with the contents of the tar
- from the command line cd to the folder "cd PizzaBot"
- execute the program with the following command

Running the program
    From windows
        java -cp out\production\PizzaBot com.PizzaBot "5x5 (0, 0) (1, 3) (4, 4) (4, 2) (4, 2) (0, 1) (3, 2) (2, 3) (4, 1)"
    From unix
        java -cp out/production/PizzaBot com.PizzaBot "5x5 (0, 0) (1, 3) (4, 4) (4, 2) (4, 2) (0, 1) (3, 2) (2, 3) (4, 1)"
    From IntelliJ Idea
        PizzaBot.iml file is included open the project folder and execute the build configuration "PizzaBot Code Challenge"

Running the tests
    From windows
        java -cp out\test\PizzaBot;out\production\PizzaBot;lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar org.junit.runner.JUnitCore PizzaBotTest
    From unix
        java -cp out/test/PizzaBot:out/production/PizzaBot:lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore PizzaBotTest
    From IntelliJ Idea
        PizzaBot.iml file is included open the project folder and execute the build configuration "PizzaBotTest"
