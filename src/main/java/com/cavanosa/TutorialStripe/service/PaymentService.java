package com.cavanosa.TutorialStripe.service;

import com.cavanosa.TutorialStripe.http.PaymentIntentDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.key.secret}")
    String secretKey;

    public PaymentIntent paymentIntent(PaymentIntentDto paymentIntentDto) throws StripeException {
        Stripe.apiKey = secretKey;
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentIntentDto.getAmount());
        params.put("currency", PaymentIntentDto.Currency.EUR);
        params.put("description", paymentIntentDto.getDescription());
        ArrayList payment_method_types = new ArrayList();
        payment_method_types.add("card");
        params.put("payment_method_types", payment_method_types);
        return PaymentIntent.create(params);
    }
}
