package com.codingbox.sprip.payment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "PaymentTable")
@Getter @Setter
public class Payment {
	@Id
	@GeneratedValue
	private Long id;
	
	private String impUid;
	private int price;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	private Order order;
}
