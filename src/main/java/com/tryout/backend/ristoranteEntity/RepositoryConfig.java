package com.tryout.backend.ristoranteEntity;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component
public class RepositoryConfig implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(Dish.class);
    config.exposeIdsFor(Comment.class);
    config.exposeIdsFor(Customer.class);
    config.exposeIdsFor(Leader.class);
    config.exposeIdsFor(Promotion.class);
  }
}
