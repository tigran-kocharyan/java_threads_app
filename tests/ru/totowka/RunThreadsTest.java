package ru.totowka;

import org.junit.jupiter.api.Test;
import java.util.Timer;
import static org.junit.jupiter.api.Assertions.*;

class RunThreadsTest {
    @Test
    public void Check_CartMovement() throws InterruptedException {
        Cart cart = new Cart(0, 0);
        String initialPosition = cart.toString();

        Animal swan = new Animal(60, "Swan");
        Thread swanThread = new Thread(() -> cart.move(swan));
        swanThread.start();

        Thread.sleep(5000);
        String tempPosition = cart.toString();

        Timer testTimer = new Timer(true);
        testTimer.schedule(Utils.getTask(() ->
        {
            swanThread.interrupt();
        }), 5000);
        swanThread.join();
        String finalPosition = cart.toString();

        assertNotEquals(initialPosition, tempPosition);
        assertNotEquals(finalPosition, tempPosition);
        assertNotEquals(finalPosition, initialPosition);
    }
}