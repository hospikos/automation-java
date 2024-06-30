package com.practicesoftwaretesting.api.cart.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private String id;
    private int quantity;
    private Double discountPercentage;
    private String cartId;
    private String productId;
    private Product product;
}
