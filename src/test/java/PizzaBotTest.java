import com.PizzaBot;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PizzaBotTest {

    @Test(expected = RuntimeException.class)
    public void testInvalidArgs() {
        System.out.println("Inside testInvalidArgs()");
        String[] args = null;

        PizzaBot.main(args);
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidNeighborhood() {
        System.out.println("Inside testInvalidNeighborhood()");
        String[] args = {"ax5 (0, 0)"};
        PizzaBot.main(args);
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidOrders() {
        System.out.println("Inside testInvalidOrders()");
        PizzaBot pizzaBot = new PizzaBot();
        pizzaBot.takeOrders(new String[] {"5x5 (1, 3) (4, 4) (6, 8)"});
    }

    @Test
    public void testTakeOrders() {
        System.out.println("Inside testTakeOrders()");
        PizzaBot pizzaBot = new PizzaBot();
        pizzaBot.takeOrders(new String[] {"15x15 (1, 3) (4, 4) (5, 5) (12,5) (15, 14)"});
        assertEquals(pizzaBot.getNeighborhoodWidth(), 15);
        assertEquals(pizzaBot.getGetNeighborhoodHeight(), 15);
        assertEquals(pizzaBot.getDeliveriesSize(), 5);
    }

    @Test
    public void testDeliveryRoute() {
        System.out.println("Inside testDeliveryRoute()");
        PizzaBot pizzaBot = new PizzaBot();
        pizzaBot.takeOrders(new String[] {"5x5 (1, 3) (4, 4)"});
        pizzaBot.makeDeliveries();
        assertEquals(pizzaBot.getDeliveryRoute(), "ENNNDEEEND");
    }
}

