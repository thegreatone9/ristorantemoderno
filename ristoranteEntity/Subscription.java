package com.tryout.backend.ristoranteEntity;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
public class Subscription {
	
	//fields
	private List<Dish> subscriptions;
	
	//constructor
	public Subscription() {
		
	}
	
	public Subscription(List<Dish> subscriptions) {
		this.subscriptions = subscriptions;
	}

	//getters,setters
	
	public List<Dish> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Dish> subscriptions) {
		this.subscriptions = subscriptions;
	}

	
	//toString
	
	@Override
	public String toString() {
		return "Subscriptions [subscriptions=" + subscriptions + "]";
	}
	
	
}
