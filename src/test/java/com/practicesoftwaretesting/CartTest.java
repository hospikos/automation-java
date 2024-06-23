package com.practicesoftwaretesting;

import com.practicesoftwaretesting.cart.CartController;
import com.practicesoftwaretesting.cart.models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {

    private static final String PRODUCT_ID = "01J12X6ZHWSGXGNT8Y6MG5SGQK";

    CartController cartController = new CartController();

    @Test
    void testCart() {
        //Create Cart
        var createdCart = cartController.createCart()
                .as(CreateCartResponse.class);
        assertNotNull(createdCart.getId());

        //Add product to cart
        var cartId = createdCart.getId();
        var updateCartResponse = cartController.addItemToCart(cartId, new AddCartItemRequest(PRODUCT_ID, 1))
                .as(UpdateCartResponse.class);
        assertNotNull(updateCartResponse.getResult());

        //Get cart Item
        var cartDetails = cartController.getCart(cartId)
                .as(CartDetails.class);
        var productIds = cartDetails.getCartItems().stream().map(CartItem::getProductId).toList();
        assertTrue(productIds.contains(PRODUCT_ID));

        //Delete cart
        cartController.deleteCart(cartId)
                .then()
                .statusCode(204);
    }
}

