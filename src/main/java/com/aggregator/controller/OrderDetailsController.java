package com.aggregator.controller;

import static com.aggregator.constant.Constants.ORDER_SERVICE_API_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aggregator.dto.OrderDetails;
import com.aggregator.service.OrderDetailsService;
import com.order.exception.AggregatorException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = ORDER_SERVICE_API_PATH)
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService userInfoService;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable(value = "id") Long id) throws AggregatorException {
		log.info("GET | ORDER DETAILS | user id: {}", id);

		OrderDetails orderDetails = userInfoService.getOrderDetailsOfUser(id);

		log.debug("GET | ORDER DETAILS | SUCCESS");
		
		return ResponseEntity.ok(orderDetails);
	}

}
