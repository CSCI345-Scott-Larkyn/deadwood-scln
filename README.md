# deadwood-scln

type "javac *.java" in command prompt in the proper directory to compile all the files
at once otherwise you'll run into troubles compiling files one by one
then run Deadwood.java x, where x is the number of players you want

Running the program in an IDE is probably easiest but the command prompt is possible too
Just make sure to add the proper modules for javafx and have it downloaded too

There is a bug which we are 85% sure is javafx's fault. Sometimes in the dropdown menu for
the moves options, two menuItems will be written over each other. Since we don't govern the
layout of these menuItems we figure it must be some sort of glitch in how javafx lays them out.
We can't find any patters to when it happens either.