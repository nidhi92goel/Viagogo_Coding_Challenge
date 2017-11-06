//Class to represent an event happeing in the world

package event_finder;

public class Event 
{
	private int e_no;            //event number
	private float ticket_price;  //event ticket price
	
	public Event()               //Class constructor with no inputs
	{
		e_no=0;
		ticket_price=0.0f;
	}
	
	public Event(int event_no, float t_price)   //Class constructor with given inputs
	{
		e_no=event_no;
		ticket_price=t_price;
	}
	
	public int getnumber()   //Returns the event number of the Event object
	{
		return e_no;
	}
	
	public float getprice()  //Returns the ticket prive of the Event object
	{
		return ticket_price;
	}
}
