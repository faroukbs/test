package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Order;
import com.roky.thunderspi.entities.OrderItem;
import com.roky.thunderspi.entities.Product;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.repositories.UserRepo;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.roky.thunderspi.dto.PaymentInfo;
import com.roky.thunderspi.dto.Purchase;
import com.roky.thunderspi.dto.PurchaseResponse;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CheckoutServiceImpl implements ICheckoutService {


    private  IProductService productService;

    private  UserRepo customerRepository;

    public CheckoutServiceImpl(UserRepo customerRepository, IProductService productService, @Value("${stripe.key.secret}") String secretKey) {
        this.customerRepository = customerRepository;
        this.productService = productService;
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> {
            Product newProduct = new Product();
            try {
                newProduct = productService.findProdById(item.getProductId());
                newProduct.setQuantity(newProduct.getQuantity() - item.getQuantity());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            order.add(item);

        });

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        User customer = purchase.getCustomer();
        customer.add(order);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", "El-Margoumino purchase");
        params.put("receipt_email", paymentInfo.getReceiptEmail());

        return PaymentIntent.create(params);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        //
        return UUID.randomUUID().toString();
    }
}


