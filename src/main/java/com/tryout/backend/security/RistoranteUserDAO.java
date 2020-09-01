package com.tryout.backend.security;

import java.util.Optional;

public interface RistoranteUserDAO {
	
	public Optional<RistoranteUser> selectRistoranteUserByUsername(String username);
}
