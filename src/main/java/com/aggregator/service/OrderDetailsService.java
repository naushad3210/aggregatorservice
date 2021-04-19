package com.aggregator.service;

import com.aggregator.dto.OrderDetails;
import com.order.exception.AggregatorException;

public interface OrderDetailsService {
	
	OrderDetails getOrderDetailsOfUser(Long id) throws AggregatorException;

}
