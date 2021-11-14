package com.exercise.ordermatcher;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Order {
    private static final Pattern PATTERN =
            Pattern.compile("(?<side>([bB][uU][yY])|([sS][eE][lL][lL]))[ ]+(?<qty>[0-9]+)[ ]*@[ ]*(?<px>[0-9]+)([ ]+#(?<id>[0-9]+))?");
    private static final String GROUP_ID = "id";
    private static final String GROUP_SIDE = "side";
    private static final String GROUP_QUANTITY = "qty";
    private static final String GROUP_PRICE = "px";

    private final long id;
    private final Side side;
    private final long price;
    private long quantity;


    public Order(long id, Side side, long price, long quantity) {
        this.id = id;
        this.side = Objects.requireNonNull(side);
        if (price < 0) {
            throw new IllegalArgumentException("price must be >= 0");
        }
        this.price = price;
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }
        this.quantity = quantity;
    }


    public long getId() {
        return id;
    }


    public Side getSide() {
        return side;
    }


    public long getPrice() {
        return price;
    }


    public long getQuantity() {
        return quantity;
    }


    public void setQuantity(long quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity must be >= 0");
        }
        this.quantity = quantity;
    }


    public boolean isEmpty() {
        return quantity == 0;
    }

    @Override
    public String toString() {
        return side + " " + quantity + "@" + price + " #" + id;
    }

    public static Order parse(String str) {
        Matcher m = PATTERN.matcher(str);
        if (!m.matches()) {
            throw new IllegalArgumentException("Illegal order format. Expected #id buy|sell quantity@price");
        }
        String idStr = m.group(GROUP_ID);
        long id = idStr != null ? Long.valueOf(idStr) : 0L;
        Side side = Side.valueOf(m.group(GROUP_SIDE).toUpperCase());
        long price = Long.valueOf(m.group(GROUP_PRICE));
        long quantity = Long.valueOf(m.group(GROUP_QUANTITY));

        return new Order(id, side, price, quantity);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 79 * hash + Objects.hashCode(this.side);
        hash = 79 * hash + (int) (this.price ^ (this.price >>> 32));
        hash = 79 * hash + (int) (this.quantity ^ (this.quantity >>> 32));
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
        final Order other = (Order) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.side != other.side) {
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
