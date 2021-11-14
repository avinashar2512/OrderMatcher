package com.exercise.ordermatcher;

public class Trade {
    private final long activeOrderId;
    private final long passiveOrderId;
    private final long price;
    private final long quantity;


    public Trade(long activeOrderId, long passiveOrderId, long price, long quantity) {
        this.activeOrderId = activeOrderId;
        this.passiveOrderId = passiveOrderId;
        this.price = price;
        this.quantity = quantity;
    }


    public long getActiveOrderId() {
        return activeOrderId;
    }


    public long getPassiveOrderId() {
        return passiveOrderId;
    }


    public long getPrice() {
        return price;
    }


    public long getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "TRADE " + quantity + "@" + price + " (#" + activeOrderId + "/#" + passiveOrderId + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.activeOrderId ^ (this.activeOrderId >>> 32));
        hash = 47 * hash + (int) (this.passiveOrderId ^ (this.passiveOrderId >>> 32));
        hash = 47 * hash + (int) (this.price ^ (this.price >>> 32));
        hash = 47 * hash + (int) (this.quantity ^ (this.quantity >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trade other = (Trade) obj;
        if (this.activeOrderId != other.activeOrderId) {
            return false;
        }
        if (this.passiveOrderId != other.passiveOrderId) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        return true;
    }

    
    
}
