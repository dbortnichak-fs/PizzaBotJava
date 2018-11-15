package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * PizzaBot solution for code challenge. Utilizes a recursive algorithm to make orderLocations
 */

@SpringBootApplication
public class PizzaBot implements CommandLineRunner {
    private int neighborhoodWidth;
    private int neighborhoodHeight;
    private List<Coordinate> orderLocations = null;
    private StringBuilder directions = null;
    private Coordinate currentLocation = null;

    /**
     * Creates the PizzaBot, takes the pizza orders and makes the orderLocations
     *
     * @param args String array containing the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PizzaBot.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        PizzaBot pizzaBot = new PizzaBot();
        pizzaBot.takeOrders(args);
        pizzaBot.makeDeliveries();
    }

    public PizzaBot() {
        orderLocations = new ArrayList<>();
        directions = new StringBuilder();
        currentLocation = new Coordinate(0, 0);
    }

    /**
     * MakeDeliveries loops over the orderLocations, delivers the pizza and prints to stdout the delivery route
     */
    public void makeDeliveries() {
        for (Coordinate location : orderLocations) {
            deliverPizza(location);
        }
        System.out.println(getDeliveryRoute());
    }

    /**
     * DeliverPizza utilizes a recursive algorithm to move from the currentLocation to the orderLocation on the neighborhood graph
     * @param orderLocation Coordinate on a Cartesian plane for the delivery point
     * @return boolean true indicates you reached the delivery point otherwise return false
     */
    private boolean deliverPizza(Coordinate orderLocation) {
        // if we reached the location exit
        if (currentLocation.equals(orderLocation)) {
            directions.append("D");
            return true;
        }

        // otherwise move towards the orderLocation
        if (currentLocation.getX() < orderLocation.getX()) {
            directions.append("E");
            currentLocation.setX(currentLocation.getX() + 1);
        } else if (currentLocation.getX() > orderLocation.getX()) {
            directions.append("W");
            currentLocation.setX(currentLocation.getX() - 1);
        } else if (currentLocation.getY() < orderLocation.getY()) {
            directions.append("N");
            currentLocation.setY(currentLocation.getY() + 1);
        } else if (currentLocation.getY() > orderLocation.getY()) {
            directions.append("S");
            currentLocation.setY(currentLocation.getY() - 1);
        }

        // recursively call until we reach our destination
        if (deliverPizza(orderLocation)) {
            return true;
        }

        return false;
    }
    /**
     * TakeOrders parses the command line arguments to determine the size of the neighborhood and a list of orderLocations
     * @param args String array of the command line arguments
     */
    public void takeOrders(String[] args) {
        if (args == null || args.length == 0) {
            throw new RuntimeException("Invalid null or empty arguments passed to PizzaBot");
        }

        int firstSpace = args[0].indexOf(' ');
        String[] neighborhoodSizeStr = args[0].substring(0, firstSpace).trim().split("x");
        // Initialize neighborhood
        try {
            neighborhoodWidth = Integer.parseInt(neighborhoodSizeStr[0]);
            neighborhoodHeight = Integer.parseInt(neighborhoodSizeStr[1]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid non-numeric value provided for size of the neighborhood");
        }

        StringTokenizer tokenizer = new StringTokenizer(args[0].substring(firstSpace).trim(), ")", false);
        String value = null;
        int x;
        int y;
        while (tokenizer.hasMoreTokens()) {
            try {
                value = tokenizer.nextToken().trim();
                x = Integer.parseInt(value.substring(1, value.indexOf(",")));
                y = Integer.parseInt(value.substring(value.indexOf(",") + 1).trim());

                if(x > neighborhoodWidth || x < 0 || y > neighborhoodHeight || y < 0 )
                    throw new RuntimeException(
                            new StringBuilder("Invalid delivery point (").append(x).append(",").append(y).append(") is outside the dimensions of the neighborhood ").append(neighborhoodWidth)
                                    .append("x").append(neighborhoodHeight).toString());

                orderLocations.add(new Coordinate(x, y));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid non-numeric input value for delivery coordinates "+value);
            }
        }
    }

    public String getDeliveryRoute() {
        return directions.toString();
    }

    public int getNeighborhoodWidth() {
        return neighborhoodWidth;
    }

    public int getGetNeighborhoodHeight() {
        return neighborhoodHeight;
    }

    public int getDeliveriesSize() {
        return orderLocations.size();
    }
}
