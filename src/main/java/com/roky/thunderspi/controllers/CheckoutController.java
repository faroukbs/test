package com.roky.thunderspi.controllers;

import com.roky.thunderspi.dto.PaymentInfo;
import com.roky.thunderspi.dto.Purchase;
import com.roky.thunderspi.dto.PurchaseResponse;

import com.roky.thunderspi.services.CheckoutServiceImpl;
import com.roky.thunderspi.services.ICheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/checkout")
@AllArgsConstructor
public class CheckoutController {


    private final CheckoutServiceImpl checkoutService;



    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) throws Exception {

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {

        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);

        String paymentStr = paymentIntent.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }

}





