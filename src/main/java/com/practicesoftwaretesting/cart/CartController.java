package com.practicesoftwaretesting.cart;

import com.practicesoftwaretesting.cart.models.AddCartItemRequest;
import com.practicesoftwaretesting.cart.models.CartDetails;
import com.practicesoftwaretesting.cart.models.CreateCartResponse;
import com.practicesoftwaretesting.cart.models.UpdateCartResponse;
import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.common.ResponseDecorator;
import io.restassured.response.Response;

public class CartController extends BaseController {

    public ResponseDecorator<CreateCartResponse> createCart() {
        return new ResponseDecorator<>(
                baseClient().post("/carts"),
                CreateCartResponse.class
        );
    }

    public ResponseDecorator<UpdateCartResponse> addItemToCart(String cartId, AddCartItemRequest cartItem) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(cartItem)
                        .post("/carts/" + cartId),
                UpdateCartResponse.class
        );
    }

    public ResponseDecorator<CartDetails> getCart(String cartId) {
        return new ResponseDecorator<>(
                baseClient()
                        .get("/carts/" + cartId),
                CartDetails.class
        );
    }

    public ResponseDecorator<Void> deleteCart(String cartId) {
        return new ResponseDecorator<>(
                baseClient()
                        .delete("/carts/" + cartId),
                Void.class
        );
    }
}
