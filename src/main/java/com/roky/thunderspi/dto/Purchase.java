package com.roky.thunderspi.dto;

import com.roky.thunderspi.entities.Address;
import com.roky.thunderspi.entities.Order;
import com.roky.thunderspi.entities.OrderItem;
import com.roky.thunderspi.entities.User;
import lombok.Data;


import java.util.Set;

@Data
public class Purchase {

    private User customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}