package com.aggregator.client;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.aggregator.common.AggregatorConfigReader;
import com.aggregator.constant.AggregatorServiceErrorCodes;
import com.aggregator.dto.Orders;
import com.aggregator.dto.UserInfo;
import com.order.exception.AggregatorException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderDetailsServiceClient {

	@Autowired
	private AggregatorConfigReader aggregatorConfigReader;

	@Autowired
	private RestTemplate restTemplate;

	public UserInfo getUserInfo(Long userId) throws AggregatorException {
		
		return (UserInfo) getForObject(StringUtils.join(aggregatorConfigReader.getUserServiceHostUrl(), StringUtils.join("/user/", userId.toString())),
				UserInfo.class, "GET_USER");
	}

	public Orders getOrdersOfUser(Long userId) throws AggregatorException {
		return (Orders) getForObject(
				StringUtils.join(aggregatorConfigReader.getOrderServiceHostUrl(), StringUtils.join("/orders/user/", userId.toString())), Orders.class,
				"GET_ORDERS");
	}

	private Object getForObject(String url, Class<?> responseType, String requestType)
			throws AggregatorException {
		Object response = null;
		log.info("OrderDetailsServiceClient | GET | requestType: {}", requestType);

		try {
			response = restTemplate.getForObject(url, responseType);
		} catch (RestClientException ex) {
			log.error("OrderDetailsServiceClient | GET | requestType: {} | ERROR | response: {}", requestType, ex.getMessage());
			throw new AggregatorException(Optional.of(AggregatorServiceErrorCodes.REST_CLIENT_EXCEPTION.getHttpCode()),
					AggregatorServiceErrorCodes.REST_CLIENT_EXCEPTION.getErrorCode(),
					AggregatorServiceErrorCodes.REST_CLIENT_EXCEPTION.getErrorMessage());
		}

		log.info("OrderDetailsServiceClient | GET | requestType: {} | SUCCESS | response: {}", requestType, response);

		return response;
	}
}
