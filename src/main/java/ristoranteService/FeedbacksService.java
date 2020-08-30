package ristoranteService;

import java.util.List;

import ristoranteEntity.Feedback;

public interface FeedbacksService {

	public List<Feedback> getAll();
	
	public Feedback findById(int theId);
	
	public void add(Feedback theFeedback);
	
	public void deleteById(int theId);
}
