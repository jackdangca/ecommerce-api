package com.api.ecommerce.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ecommerce.manager.StripeClient;
import com.api.ecommerce.model.PaymentRequest;
import com.stripe.model.Charge;
import com.stripe.model.WebhookEndpoint;

@RestController
@RequestMapping("/stripe")
public class PaymentController {

	private StripeClient stripeClient;

	@Autowired
	PaymentController(StripeClient stripeClient) {
		this.stripeClient = stripeClient;
	}

	/**
	 * Returns Charge
	 *
	 * @param request - Payment Request
	 * @return - Charge
	 */
	@PostMapping("/charge")
	public Charge chargeCard(@RequestBody PaymentRequest request) throws Exception {
		String token = request.getStripeToken();
		Integer amount = request.getAmount();
		Long orderId = request.getOrderId();
		String description = request.getDescription();
		String currency = request.getCurrency();
		return this.stripeClient.chargeCreditCard(token, amount, orderId, description, currency);
	}

	/**
	 * Returns String
	 *
	 * @return - String
	 */
	@PostMapping("/webhooks")
	public String createWebhook() throws Exception {
		Map<String, Object> webhookendpointParams = new HashMap<String, Object>();
		webhookendpointParams.put("url", "https://example.com/my/webhook/endpoint");
		webhookendpointParams.put("enabled_events", Arrays.asList("charge.failed", "charge.succeeded"));
		try {
			WebhookEndpoint.create(webhookendpointParams);
		} catch (Exception e) {
			throw e;
		}

		return "This endpoint is used by Stripe.";
	}
}
