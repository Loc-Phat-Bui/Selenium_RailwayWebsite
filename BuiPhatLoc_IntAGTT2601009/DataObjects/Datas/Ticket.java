package Datas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import Constant.SeatType;
import Constant.StationLocation;
import Datas.Account.AccountInfo;

public class Ticket {
	public static class TicketInfo {
	    private short interval;
	    private String departFrom;
	    private String arriveAt;
	    private String seatType;
	    private byte ticketAmount;

	    public TicketInfo(short interval, String departFrom, String arriveAt, String seatType, byte ticketAmount) {
	        this.interval = interval;
	        this.departFrom = departFrom;
	        this.arriveAt = arriveAt;
	        this.seatType = seatType;
	        this.ticketAmount = ticketAmount;
	    }
	    public short getDepartDateInterval() { return this.interval; }
	    public String getDepartFrom() { return this.departFrom; }
	    public String getArriveAt() { return this.arriveAt; }
	    public String getSeatType() { return this.seatType; }
	    public byte getTicketAmount() { return this.ticketAmount; }

	    public void setDepartDateInterval(short interval) { this.interval = interval; }
	    public void setDepartFrom(String departFrom) { this.departFrom = departFrom; }
	    public void setArriveAt(String arriveAt) { this.arriveAt = arriveAt; }
	    public void setSeatType(String seatType) { this.seatType = seatType; }
	    public void setTicketAmount(byte ticketAmount) { this.ticketAmount = ticketAmount; }
	}
	
	private static final Map<String, TicketInfo> ticketMap = new HashMap<String, Ticket.TicketInfo>();
	
	static {
		ticketMap.put("TC12", new TicketInfo(
				(short) 2,
				StationLocation.NHA_TRANG.getDisplayName(),
				StationLocation.HUE.getDisplayName(),
				SeatType.SOFT_BED_AC.getDescription(),
				(byte) 1));
		ticketMap.put("TC13", new TicketInfo(
				(short) 25,
				StationLocation.NHA_TRANG.getDisplayName(),
				StationLocation.SAI_GON.getDisplayName(),
				SeatType.SOFT_BED_AC.getDescription(),
				(byte) 5));
		ticketMap.put("TC15", new TicketInfo(
				(short) 1,
				"",
				"",
				"",
				(byte) 5));
	}
	
	public static TicketInfo getTicketInfo (String tcID) {
		TicketInfo ticket = ticketMap.get(tcID);
		return ticket;
	}
}
