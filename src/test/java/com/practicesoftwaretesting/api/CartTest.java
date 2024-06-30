package com.practicesoftwaretesting.api;

import com.practicesoftwaretesting.api.cart.CartController;
import com.practicesoftwaretesting.api.cart.models.AddCartItemRequest;
import com.practicesoftwaretesting.api.cart.models.CartItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {

    private static final String PRODUCT_ID = "01J1MQ4A3KX7JBXHSTA8CY031Q";

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

