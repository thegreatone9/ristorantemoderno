package com.tryout.backend.retired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
class SecondController {
	
	@RequestMapping(value="/api", headers = "Accept=application/json", method = RequestMethod.POST)
	public RestaurantCustomer respond(@RequestBody RestaurantCustomer json_data) throws JsonMappingException, JsonProcessingException {
		try {
			//RestaurantCustomer user_rec = objectMapper.readValue(json_data, RestaurantCustomer.class);
			System.out.println("Object Mapping successful!");
			//System.out.println(user_rec);
			System.out.println(json_data.getData());
		    return json_data;
		}
		
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("ERROR!");
			System.out.println("This is what we received: " + json_data);
			return null;
		}
	}
	
	
}


class RestaurantCustomer {
	private String data;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "User name is: " + this.data;
	}
}