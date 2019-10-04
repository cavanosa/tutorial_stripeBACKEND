package com.cavanosa.TutorialStripe.controller;

import com.cavanosa.TutorialStripe.http.PaymentIntentDto;
import com.cavanosa.TutorialStripe.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stripe")
@CrossOrigin(origins = "*")
public class StripeController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/paymentintent")
    public ResponseEntity<String> payment(@RequestBody PaymentIntentDto paymentIntentDto) throws StripeException {
        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentDto);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
}
