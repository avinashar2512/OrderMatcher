

package com.exercise.ordermatcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


public class OrderMatcher {


    public OrderMatcher() {
    }
    

    public List<Trade> addOrder(Order order) {
        throw new UnsupportedOperationException("addOrder is not implemented yet"); // FIXME
    }


    public List<Order> getOrders(Side side) {
        throw new UnsupportedOperationException("getOrders is not implemented yet"); // FIXME
    }



    public static void main(String... args) throws Exception {
        OrderMatcher matcher = new OrderMatcher();
        System.out.println("Welcome to the order matcher. Type 'help' for a list of commands.");
        System.out.println();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        LOOP: while ((line=reader.readLine()) != null) {
            line = line.trim();
            try {
                switch(line) {
                    case "help":
                        System.out.println("Available commands: \n"
                                + "  buy|sell <quantity>@<price> [#<id>]  - Enter an order.\n"
                                + "  list                                 - List all remaining orders.\n"
                                + "  quit                                 - Quit.\n"
                                + "  help                                 - Show help (this message).\n");
                        break;
                    case "":
                        // ignore
                        break;
                    case "quit":
                        break LOOP;
                    case "list":
                        System.out.println("BUY:");
                        matcher.getOrders(Side.BUY).stream().map(Order::toString).forEach(System.out::println);
                        System.out.println("SELL:");
                        matcher.getOrders(Side.SELL).stream().map(Order::toString).forEach(System.out::println);
                        break;
                    default: // order
                        matcher.addOrder(Order.parse(line)).stream().map(Trade::toString).forEach(System.out::println);
                        break;
                }
            } catch (IllegalArgumentException e) {
               e.printStackTrace();
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
            }
        }

    }
}
