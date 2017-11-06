//Class to Create events in the world and fetch the 5 closest events to the user inputed coordinates 

package event_finder;

import java.util.Random;
import java.util.Scanner;

public class World 
{
	private static Event[][] world=new Event[21][21];  //Stores the world data
	private static int count=0;                        //Counts the number of events found
	
	//Function to create seed data to add to the world matrix
	private static void create_events()
	{
		Random random=new Random();
		int n=random.nextInt(437)+5;  //number of events in the world (min 5, max 441)
		
		boolean placed;               //variable to check if an event has been placed in the matrix
		int row;                      
		int column;
		
		//loop to add 'n' events to the world matrix
		for(int i=1;i<=n;i++)
		{
			//to generate a random floating point number for the ticket price of the event
			float ticket_price=Math.round(random.nextFloat()*10)/10.0f+random.nextInt(99)+1;
			
			Event event=new Event(i,ticket_price); //create an instance of event class
			placed=false;
			
			//loop to ensure placement of the event
			while(placed==false)
			{
				row=random.nextInt(21);      //generating a random integer for the row number
				column=random.nextInt(21);   //generating a random integer for the column number
				
				//condition to check if the random point generated is occupied and place 
				//the event there if it's not
				if(world[row][column]==null)
				{
					world[row][column]=event;
					placed=true;
				}
			}
		}
	}
	
	//function to find events closest to the user inputed coordinates of a point
	private static void find_events(int x,int y)
	{
		int x1;
		int y1;
		
		check_event(x,y,0);  //function call to check if the point contains an event
		
		//loop to spiral around the user inputed point
		for(int d=1;d<21;d++)
		{
			for(int i=0;i<=d;i++)
			{
				//the points at the lower left side of the user inputer point
				x1=x-i;
				y1=y-d+i;
					
				check_event(x1,y1,d); //function call to check if the point contains an event
				
				//the points at the upper left side of the user inputer point
				x1=x+i;
				y1=y+d-i;
				
				check_event(x1,y1,d);
			}
				
			for(int i=1;i<=d;i++)
			{
				//the points at the upper left side of the user inputer point
				x1=x-i;
				y1=y+d-i;
					
				check_event(x1,y1,d); //function call to check if the point contains an event
				
				//the points at the lower right side of the user inputer point
				x1=x+i;
				y1=y-d+i;
				
				check_event(x1,y1,d);
			}	
		}
	}
	
	//to check if a point contains an event and display it
	private static void check_event(int x,int y, int distance)
	{
		int number;
		float price;
		
		if(x<21 && y<21 && x>-1 && y>-1) 
		{
			if(world[x][y]!=null)  //condition to check if a point contains an event
			{
				number=world[x][y].getnumber();
				price=world[x][y].getprice();
			
				System.out.println("Event "+ String.format("%03d", number)+" - $"+price+"0, Distance "+distance);
				count++;   //variable to keep count of number of events displayed
				
				if(count==5)
				 System.exit(0);  //terminates the program when 5 events have been found
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		boolean correct_input=false;  //variable to ensure valid user input
		String input;
		String[] coordinates = null;
		int x = 0;  //x coordinate of user inputed point
		int y = 0;  //y coordinate of user inputed point
		
		create_events();   //function call to create seed data to fill the world matrix with events
		
		while(correct_input==false)   //loop to take user input and ensure its validity
		{
			System.out.println("Please Input Coordinates (seperated by a comma eg 4,2):");		
			input=scan.next();
			
			if(input.contains(","))
			{
				coordinates=input.split(",");
				
				if(coordinates.length!=2)
				{
					correct_input=false;
					continue;
				}
				
				if(coordinates[0]==null || coordinates[0].isEmpty() || coordinates[1]==null || coordinates[1].isEmpty())
				{
					correct_input=false;
					continue;
				}
				
				if(!coordinates[0].matches("^[-]?[0-9]*$") || !coordinates[1].matches("^[-]?[0-9]*$"))
				{
					correct_input=false;
					continue;
				}

				x=Integer.parseInt(coordinates[0]);
				y=Integer.parseInt(coordinates[1]);
				
				if(x>10 || x<-10 || y>10 || y<-10)
				{
					correct_input=false;
					continue;
				}
				
				correct_input=true;
			}
			else
			{
				correct_input=false;
			}
		}
		
		find_events(x+10,y+10);  //function call to find 5 events near the user given point
		
		scan.close();
	}
}
