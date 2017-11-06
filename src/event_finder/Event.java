package event_finder;

public class Event 
{
	private int e_no;
	private float ticket_price;
	
	public Event()
	{
		e_no=000;
		ticket_price=0.0f;
	}
	
	public Event(int event_no, float t_price)
	{
		e_no=event_no;
		ticket_price=t_price;
	}
	
	public void setnumber(int event_no)
	{
		e_no=event_no;
	}
	
	public int getnumber()
	{
		return e_no;
	}
	
	public void setprice(float t_price)
	{
		ticket_price=t_price;
	}
	
	public float getprice()
	{
		return ticket_price;
	}
}
