package com.aggregator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aggregator.client.OrderDetailsServiceClient;
import com.aggregator.dto.OrderDetails;
import com.aggregator.dto.Orders;
import com.aggregator.dto.UserInfo;
import com.aggregator.service.OrderDetailsService;
import com.order.exception.AggregatorException;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsServiceClient aggregatorServiceClient;
	
	@Override
	public OrderDetails getOrderDetailsOfUser(Long userId) throws AggregatorException {
		
		UserInfo userInfo = aggregatorServiceClient.getUserInfo(userId);
		Orders orders = aggregatorServiceClient.getOrdersOfUser(userId);
		
		return new OrderDetails(userInfo, orders.getOrders());
	}

	

	
}
