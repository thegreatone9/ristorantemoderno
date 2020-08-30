package ristoranteService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ristoranteDAO.PromotionsRepository;
import ristoranteEntity.Promotion;

@Service
public class PromotionsServiceImpl implements PromotionsService {
	
	private PromotionsRepository PromotionsRepository;
	
	@Autowired //qualifier given to identify which dao impl to use, as the context contains two beans corresponding to two impls 
	public PromotionsServiceImpl(PromotionsRepository thePromotionsRepository) {
		this.PromotionsRepository = thePromotionsRepository;
	}

	@Override
	public void deleteById(int theId) {
		
		PromotionsRepository.deleteById(theId);

	}

	@Override
	//@Transactional //org.springframework.Transactional update: no need with JPA repo, as its provided out of box
	public List<Promotion> getAll() {
		
		return PromotionsRepository.findAll();
	}

	@Override
	public Promotion findById(int theId) {
		
		//Optionals!!! because we may not find by that id
		Optional<Promotion> result = PromotionsRepository.findById(theId);
		
		Promotion thePromotion = null;
		
		if (result.isPresent()) {
			thePromotion = result.get();
		}
		
		else {
			throw new RuntimeException("Did not find Promotion id - " + theId);
		}
		
		return thePromotion;
	}

	@Override
	public void add(Promotion thePromotion) {
		
		PromotionsRepository.save(thePromotion);
		
	}

}
