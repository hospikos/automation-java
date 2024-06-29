package com.practicesoftwaretesting;

import com.practicesoftwaretesting.cart.CartController;
import com.practicesoftwaretesting.cart.models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {

    private static final String PRODUCT_ID = "01J1JF15DGXK883V8S7JVXK3GH";

    CartController cartController = new CartController();

    @Test
    void testCart() {
        //Create Cart
        var createdCart = cartController.createCart()
                .assertStatusCode(201)
                .as();
        assertNotNull(createdCart.getId());

        //Add product to cart
        var cartId = createdCart.getId();
        var updateCartResponse = cartController.addItemToCart(cartId, new AddCartItemRequest(PRODUCT_ID, 1))
                .assertStatusCode(200)
                .as();
        assertNotNull(updateCartResponse.getResult());

        //Get cart Item
        var cartDetails = cartController.getCart(cartId)
                .assertStatusCode(200)
                .as();
        var productIds = cartDetails.getCartItems().stream().map(CartItem::getProductId).toList();
        assertTrue(productIds.contains(PRODUCT_ID));

        //Delete cart
        cartController.deleteCart(cartId)
                .assertStatusCode(204);
    }
}

