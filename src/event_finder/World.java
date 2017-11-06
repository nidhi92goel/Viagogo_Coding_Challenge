package event_finder;

import java.text.DecimalFormat;
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
		
		while(count<5)
		{
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
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		create_events();
		
		System.out.println("Please Input Coordinates (coordinates seperated by comma eg 4,2):");
		String input=scan.next();
		
		int x=Integer.parseInt(input.split(",")[0])+10;
		int y=Integer.parseInt(input.split(",")[1])+10;
		
		find_events(x,y);
		
		/*
		int count=0;
		for(int i=0;i<21;i++)
		{
			for(int j=0;j<21;j++)
			{
				if(world[i][j]==null)
					System.out.print("..."+"   ");
				else
				{
					System.out.print(String.format("%03d", world[i][j].getnumber())+"   ");
				}
			}
		    System.out.println();
		}
		*/
		
	}
}
