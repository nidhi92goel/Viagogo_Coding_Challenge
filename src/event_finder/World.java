package event_finder;

import java.util.Random;
import java.util.Scanner;

public class World 
{
	private static Event[][] world=new Event[21][21];
	private static int count=0;
	
	private static void create_events()
	{
		Random random=new Random();
		int n=random.nextInt(437)+5; //minimum 5 events and maximum 441 events
		
		boolean placed;
		int row;
		int column;
		
		for(int i=1;i<=n;i++)
		{
			float ticket_price=Math.round(random.nextFloat()*10)/10.0f+random.nextInt(99)+1;
			
			Event event=new Event(i,ticket_price);
			
			placed=false;
			
			while(placed==false)
			{
				row=random.nextInt(21);
				column=random.nextInt(21);
				
				if(world[row][column]==null)
				{
					world[row][column]=event;
					placed=true;
				}
			}
		}
	}
	
	private static void find_events(int x,int y)
	{
		int x1;
		int y1;
		
		int number;
		float price;
		
		check_event(x,y,0);
		
		for(int d=1;d<21;d++)
		{
			for(int i=0;i<=d;i++)
			{
				x1=x-i;
				y1=y-d+i;
					
				check_event(x1,y1,d);
					
				x1=x+i;
				y1=y+d-i;
				
				check_event(x1,y1,d);
			}
				
			for(int i=1;i<=d;i++)
			{
				x1=x-i;
				y1=y+d-i;
					
				check_event(x1,y1,d);
					
				x1=x+i;
				y1=y-d+i;
				
				check_event(x1,y1,d);
			}	
		}
	}
	
	private static void check_event(int x,int y, int distance)
	{
		int number;
		float price;
		
		if(x<21 && y<21)
		{
			if(world[x][y]!=null)
			{
				number=world[x][y].getnumber();
				price=world[x][y].getprice();
			
				System.out.println("Event "+ String.format("%03d", number)+" - $"+price+"0, Distance "+distance);
				count++;
				
				if(count==5)
				 System.exit(0);
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		boolean correct_input=false;
		String input;
		String[] coordinates = null;
		int x = 0;
		int y = 0;
		
		create_events();
		
		while(correct_input==false)
		{
			System.out.println("Please Input Coordinates (coordinates seperated by comma eg 4,2):");		
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

		find_events(x+10,y+10);
	}
}
