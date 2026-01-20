package com.apigateway.repo;


import com.apigateway.entity.ApiDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ApiDefinitionRepo extends JpaRepository<ApiDefinition, String> {

    Optional<ApiDefinition> findById(String id);

    Optional<ApiDefinition> findByApiName(String apiName);

}
