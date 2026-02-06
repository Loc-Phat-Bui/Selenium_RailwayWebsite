package Datas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Ticket {
	public static class TicketInfo {
	    private String departDate;
	    private String departFrom;
	    private String arriveAt;
	    private String seatType;
	    private byte ticketAmount;

	    public TicketInfo(String departDate, String departFrom, String arriveAt, String seatType, byte ticketAmount) {
	        this.departDate = departDate;
	        this.departFrom = departFrom;
	        this.arriveAt = arriveAt;
	        this.seatType = seatType;
	        this.ticketAmount = ticketAmount;
	    }
	    public String getDepartDate() { return this.departDate; }
	    public String getDepartFrom() { return this.departFrom; }
	    public String getArriveAt() { return this.arriveAt; }
	    public String getSeatType() { return this.seatType; }
	    public byte getTicketAmount() { return this.ticketAmount; }

	    public void setDepartDate(String departDate) { this.departDate = departDate; }
	    public void setDepartFrom(String departFrom) { this.departFrom = departFrom; }
	    public void setArriveAt(String arriveAt) { this.arriveAt = arriveAt; }
	    public void setSeatType(String seatType) { this.seatType = seatType; }
	    public void setTicketAmount(byte ticketAmount) { this.ticketAmount = ticketAmount; }
	}
	
	private static final Map<String, TicketInfo> ticketMap = new HashMap<String, Ticket.TicketInfo>();
	
	static {
		ticketMap.put("TC12", new TicketInfo(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddyy_")), 
				"Nha Trang", 
				"Huế", 
				"Soft bed with air conditioner", 
				(byte) 1));
	}
}
