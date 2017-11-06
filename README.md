# Viagogo_Coding_Challenge
Program to find the nearest events to a user inputed coordinates

This program generates seed data for a world ranging from -10 to 10 on X and Y axis which contains events at different points. The program then takes user input of coordinates and returns a list of 5 events which are closest to this point along with the ticket prices and the distance they are at.

>Event class represents an event which contains the Event Number and the price of a ticket (It is assumed that each event contains only 1 kind of ticket)

>World is represented by a 21x21 2D Matrix containing Event objects

>Number of events in the world is chosen using random number generator which generates a number between 5 and 441 (It is assumed that a minimum of 5 events are present in the world)

>These events are then added to random points in the matrix and its ensured that each event is placed at a point and that each point has a maximum of 1 event

>User input is taken and a series of conditions ensure that the input is correct before proceeding

>An algorithm is designed to calculate the nearest 5 events (in terms of Manhattan distance) to the coordinates inputed and this list is returned to the user. After this the program terminates

Q How might you change your program if you needed to support multiple events at the same location? A If there were multiple events at the same location, the Matrix would then contain a new kind of object called Point. Each object of Point class would contain an ArrayList of Event objects. When assigning places to the events in the matrix, the ArrayList at a point would add the particular event.
To find the nearest events to inputed coordinates, all the events at a particular Point would be returned to the user before checking the next Point.

Q How would you change your program if you were working in a much larger world size? A In a much larger world size, the size of the matrix representing the world would change. I would limit the number of events that can be randomly added to the matrix.
