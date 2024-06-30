package com.practicesoftwaretesting.api.cart.models;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AddCartItemRequest {

    private String productId;
    private int quantity;
}
