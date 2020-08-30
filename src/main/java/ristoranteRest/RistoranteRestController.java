package ristoranteRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ristoranteEntity.Comment;
import ristoranteEntity.Dish;
import ristoranteEntity.Feedback;
import ristoranteEntity.Leader;
import ristoranteEntity.Promotion;
import ristoranteEntity.User;
import ristoranteService.CommentsService;
import ristoranteService.DishesService;
import ristoranteService.FeedbacksService;
import ristoranteService.LeadersService;
import ristoranteService.PromotionsService;
import ristoranteService.UsersService;

@RestController
@RequestMapping("/api")
public class RistoranteRestController {
	
	// quick and dirty autowire inject employee DAO
	@Autowired
	private DishesService dishesService;
	
	// quick and dirty autowire inject employee DAO
	@Autowired
	private LeadersService leadersService;
	
	// quick and dirty autowire inject employee DAO
	@Autowired
	private UsersService usersService;
	
	// quick and dirty autowire inject employee DAO
	@Autowired
	private CommentsService commentsService;
	
	// quick and dirty autowire inject employee DAO
	@Autowired
	private PromotionsService promotionsService;
	
	// quick and dirty autowire inject employee DAO
	@Autowired
	private FeedbacksService feedbacksService;
	
	
	
	
	//expose "/employees" and return list of employees
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
	@GetMapping("/feedbacks") 
	public List<Feedback> getFeedbacks() throws Exception{
		   
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		return feedbacksService.getAll();
	}    
	
}
