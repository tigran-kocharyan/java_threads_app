package ru.totowka;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    @Test
    public void Create_CartWithDoubles() {
        Cart cart = new Cart(1.5, 1.5);
        assertEquals(cart.getCoordinates(), new Point(1.5, 1.5));
    }

    @Test
    public void Add_DoublesToCart() {
        Cart cart = new Cart(1.5, 1.5);
        cart.getCoordinates().add(1.5, 1.5);
        assertEquals(cart.getCoordinates(), new Point(1.5 + 1.5, 1.5 + 1.5));
    }

    @Test
    public void Add_PositiveNumber() {
        Cart cart = new Cart(-1.5, -1.5);
        cart.getCoordinates().add(1, 1);
        assertEquals(cart.getCoordinates(), new Point(-1.5 + 1, -1.5 + 1));
    }

    @Test
    public void Add_NegativeNumber() {
        Cart cart = new Cart(-1.5, -1.5);
        cart.getCoordinates().add(-1, -1);
        assertEquals(cart.getCoordinates(), new Point(-1.5 - 1, -1.5 - 1));
    }

    @Test
    public void Check_Formats() throws ParseException {
        Cart cartDouble = Utils.getCart(new String[]{"1,5", "1,5"});
        Cart cartInt = Utils.getCart(new String[]{"1", "1"});
        assertEquals(cartDouble.getCoordinates(), new Point(1.5, 1.5));
        assertEquals(cartInt.getCoordinates(), new Point(1, 1));
    }

    @Test
    public void Check_OneParam() throws ParseException {
        // Данный тест привязан к Вашей локали. Будьте внимательным, чтобы случайно не ошибиться.
        // Тест может давать ошибку при сравнении, если в Вашей локали разделитель - точка.
        Cart cart = Utils.getCart(new String[]{"1,5"});
        assertEquals(cart.getCoordinates(), new Cart(1.5,0).getCoordinates());
    }

    @Test
    public void Check_NoParams() throws ParseException {
        Cart cart = Utils.getCart(new String[]{});
        assertEquals(cart.getCoordinates(), new Cart(0,0).getCoordinates());
    }

    @Test
    public void Check_ParamsAsNull() {
        assertThrows(NullPointerException.class, () -> {
            Utils.getCart(new String[]{null, null});
        });
    }

    @Test
    public void Check_ParamsAsNotNumber() {
        assertThrows(ParseException.class, () -> {
            Utils.getCart(new String[]{"a", "b"});
        });
    }
}