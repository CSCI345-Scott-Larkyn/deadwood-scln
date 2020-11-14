# deadwood-scln

type "javac *.java" in command prompt in the proper directory to compile all the file at once
otherwise you'll run into troubles compiling files one by one
then run Deadwood.java x, where x is the number of players you want

for the ASCI board, it's a bit difficult to readbut helpful if you can understand it
most rooms have three boxes in them like below that can hold players
+---+
|   |
+---+
each player has a one character ID corresponding to their number
each ID is the same as shift + Player number
the different boxes are all labelled either card, ex, or vis
card is for the card in scenes and where on card roles are
ex is for off-card roles (extras)
vis is for visitors, those who are in the location but do not have a role
the trailers and casting office only have a vis box for obvious reasons

each location has a sometimes shortened name printed in it with colon then a number
that number is how many shots are left to be completed before the scene wraps

the cards are not hidden until players show up as the rules say they should be
it is simply to help players to know where they are and what options are available
for the GUI version we will of course implement this