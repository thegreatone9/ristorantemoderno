package com.tryout.backend.ristoranteRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tryout.backend.ristoranteEntity.Comment;
import com.tryout.backend.ristoranteEntity.Dish;
import com.tryout.backend.ristoranteEntity.Feedback;
import com.tryout.backend.ristoranteEntity.Leader;
import com.tryout.backend.ristoranteEntity.Promotion;
import com.tryout.backend.ristoranteEntity.User;
import com.tryout.backend.ristoranteService.CommentsService;
import com.tryout.backend.ristoranteService.DishesService;
import com.tryout.backend.ristoranteService.FeedbacksService;
import com.tryout.backend.ristoranteService.LeadersService;
import com.tryout.backend.ristoranteService.PromotionsService;
import com.tryout.backend.ristoranteService.UsersService;

@RestController
@RequestMapping("/api")
public class RistoranteRestController {
	
	private DishesService dishesService;
		
	private LeadersService leadersService;
	
	private UsersService usersService;
	
	private CommentsService commentsService;
	
	private PromotionsService promotionsService;
	
	private FeedbacksService feedbacksService;
	
	
	@Autowired
	public RistoranteRestController(DishesService dishesService, LeadersService leadersService,
			UsersService usersService, CommentsService commentsService, PromotionsService promotionsService,
			FeedbacksService feedbacksService) {
		this.dishesService = dishesService;
		this.leadersService = leadersService;
		this.usersService = usersService;
		this.commentsService = commentsService;
		this.promotionsService = promotionsService;
		this.feedbacksService = feedbacksService;
	}

	//expose "/dishes" and return list of employees
	@GetMapping("/dishes") 
	public List<Dish> getDishes() throws Exception{
		
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		return dishesService.getAll();
	}
	
	//expose "/employees" and return list of employees
	@GetMapping("/users") 
	public List<User> getUsers() throws Exception{
		
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		return usersService.getAll();
	}
	
	//expose "/employees" and return list of employees
	@GetMapping("/comments") 
	public List<Comment> getComments() throws Exception{
		   
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		return commentsService.getAll();
	}      
	       
	//expose "/employees" and return list of employees
	@GetMapping("/leaders") 
	public List<Leader> getLeaders() throws Exception{
		   
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		return leadersService.getAll();
	}
	
	//expose "/employees" and return list of employees
	@GetMapping("/promotions") 
	public List<Promotion> getPromotions() throws Exception{
		   
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		return promotionsService.getAll();
	}    
	
	
	//expose "/employees" and return list of employees
	@GetMapping("/feedback") 
	public List<Feedback> getFeedbacks() throws Exception{
		   
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		return feedbacksService.getAll();
	}    
	
}
