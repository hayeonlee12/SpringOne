package com.codingbox.sprip.payment;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	public List<Order> findOrder(Long orderid) {
		return orderRepository.findOne(orderid);
	}
}
