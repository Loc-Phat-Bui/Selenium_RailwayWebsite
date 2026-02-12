package RailwayDatas;

import java.util.HashMap;
import java.util.Map;

public class TicketPrice {
    public static class TicketPriceInfo {
        private String priceHS;
        private String priceSS;
        private String priceSSC;
        private String priceHB;
        private String priceSB;
        private String priceSBC;

        public TicketPriceInfo(String priceHS, String priceSS, String priceSSC, String priceHB, String priceSB, String priceSBC) {
            this.priceHS = priceHS;
            this.priceSS = priceSS;
            this.priceSSC = priceSSC;
            this.priceHB = priceHB;
            this.priceSB = priceSB;
            this.priceSBC = priceSBC;
        }

        // Getters
        public String getPriceHS() { return priceHS; }
        public String getPriceSS() { return priceSS; }
        public String getPriceSSC() { return priceSSC; }
        public String getPriceHB() { return priceHB; }
        public String getPriceSB() { return priceSB; }
        public String getPriceSBC() { return priceSBC; }

        // Setters
        public void setPriceHS(String priceHS) { this.priceHS = priceHS; }
        public void setPriceSS(String priceSS) { this.priceSS = priceSS; }
        public void setPriceSSC(String priceSSC) { this.priceSSC = priceSSC; }
        public void setPriceHB(String priceHB) { this.priceHB = priceHB; }
        public void setPriceSB(String priceSB) { this.priceSB = priceSB; }
        public void setPriceSBC(String priceSBC) { this.priceSBC = priceSBC; }
    }

    private static final Map<String, TicketPriceInfo> priceMap = new HashMap<>();

    static {
        // Example data mapping a Ticket ID or Route ID to its price set
        priceMap.put("TC14", new TicketPriceInfo(
        		"310000", 
        		"335000", 
        		"360000", 
        		"410000", 
        		"460000", 
        		"510000"));
    }

    public static TicketPriceInfo getTicketPriceInfo(String tcID) {
        return priceMap.get(tcID);
    }
}