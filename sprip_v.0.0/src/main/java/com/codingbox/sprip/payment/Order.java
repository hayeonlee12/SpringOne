package com.codingbox.sprip.payment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "PurchaseOrder")
@Getter
@Setter
public class Order {
	@Id
	@GeneratedValue
	private long orderId;
	
	@OneToOne(mappedBy="order")
	private Payment payment;
}
