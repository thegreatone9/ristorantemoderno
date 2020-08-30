package ristoranteService;

import java.util.List;

import ristoranteEntity.Leader;

public interface LeadersService {

	public List<Leader> getAll();
	
	public Leader findById(int theId);
	
	public void add(Leader theLeader);
	
	public void deleteById(int theId);
}
