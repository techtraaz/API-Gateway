package com.apigateway.repo;


import com.apigateway.entity.ApiDefinition;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApiDefinitionRepo extends JpaRepository<ApiDefinition, String> {

}
