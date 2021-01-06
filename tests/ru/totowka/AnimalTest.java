package ru.totowka;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    public void Check_NameAsNull() {
        Cart cart = new Cart(0, 0);
        assertThrows(NullPointerException.class, () -> {
            new Animal(60, null);
        });
    }

    @Test
    public void Check_AnimalAsNull() {
        Cart cart = new Cart(0, 0);
        assertThrows(NullPointerException.class, () -> {
            cart.move(null);
        });
    }
}