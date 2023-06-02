package com.roky.thunderspi.services;

import com.roky.thunderspi.dto.PaymentInfo;
import com.roky.thunderspi.dto.Purchase;
import com.roky.thunderspi.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface ICheckoutService {
    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;

    PurchaseResponse placeOrder(Purchase purchase) throws Exception;
}
