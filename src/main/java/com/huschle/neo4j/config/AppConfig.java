package com.huschle.neo4j.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.data.neo4j.rest.SpringCypherRestGraphDatabase;
//import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

import com.huschle.neo4j.CandidateRepository;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.huschle.neo4j")
public class AppConfig extends Neo4jConfiguration  {
    public AppConfig() {
        setBasePackage("com.huschle.neo4j");
    }
    @Autowired
    GraphDatabase graphDatabase;
    @Autowired
    CandidateRepository candidateRepository;  
    //@SuppressWarnings("deprecation")
	@Bean
    GraphDatabaseService graphDatabaseService() {
    	return new SpringCypherRestGraphDatabase("http://localhost:7474/db/data", "neo4j", "neo4j");
    	//return new SpringRestGraphDatabase("http://localhost:7474/db/data");
    }
    
}