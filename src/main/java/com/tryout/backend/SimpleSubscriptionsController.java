package com.tryout.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SimpleSubscriptionsController {
	
	private String subscriptions = "[ { \"breakfast\": [ { \"id\": 0, \"name\": \"Uthappizza\", \"image\": \"images/uthappizza.png\", \"category\": \"mains\", \"label\": \"Hot\", \"price\": \"4.99\", \"featured\": true, \"description\": \"A unique combination of Indian Uthappam (pancake) and Italian pizza, topped with Cerignola olives, ripe vine cherry tomatoes, Vidalia onion, Guntur chillies and Buffalo Paneer.\" }, { \"id\": 1, \"name\": \"Zucchipakoda\", \"image\": \"images/zucchipakoda.png\", \"category\": \"appetizer\", \"label\": \"\", \"price\": \"1.99\", \"featured\": false, \"description\": \"Deep fried Zucchini coated with mildly spiced Chickpea flour batter accompanied with a sweet-tangy tamarind sauce\" } ] }, { \"lunch\": [ { \"id\": 2, \"name\": \"Vadonut\", \"image\": \"images/vadonut.png\", \"category\": \"appetizer\", \"label\": \"New\", \"price\": \"1.99\", \"featured\": false, \"description\": \"A quintessential ConFusion experience, is it a vada or is it a donut?\" }, { \"id\": 3, \"name\": \"ElaiCheese Cake\", \"image\": \"images/elaicheesecake.png\", \"category\": \"dessert\", \"label\": \"\", \"price\": \"2.99\", \"featured\": false, \"description\": \"A delectable, semi-sweet New York Style Cheese Cake, with Graham cracker crust and spiced with Indian cardamoms\" } ] } ]";
	
	@RequestMapping(value="/subscriptions", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> subscriptions() {
		System.out.println("get request received, returning subscriptions!");
		return ResponseEntity.ok(subscriptions);
	}
}
